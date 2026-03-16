# Project Management - Create Project - Test Scenarios

## 1. Doküman Amacı

Bu doküman, **Kanboard > Project Management > Create Project** alt modülü için risk bazlı ve otomasyona uygun test senaryo setini tanımlar.

Amaç:
- yeni proje oluşturma davranışını doğru modül sınırı içinde ele almak,
- authorization, validation, access/visibility ve UI-API consistency risklerini kapsamak,
- gereksiz tekrar oluşturmadan test case üretimine uygun bir senaryo omurgası kurmak,
- sonradan UI automation, API automation ve hybrid test tasarımına temiz geçiş sağlamaktır.

Bu doküman step-by-step test case içermez. Test case dokümanı için temel senaryo kaynağıdır.

---

## 2. Modül Kapsamı

### Kapsam Dahili
- team project oluşturma
- personal project oluşturma
- create ekranı temel validation davranışları
- application role bazlı create authorization davranışı
- create sonrası görünürlük ve temel persistence doğrulaması
- UI ve User API katmanları arasındaki davranış tutarlılığı

### Kapsam Dışı
- existing project üzerinden copy/duplicate create davranışları
- edit project
- default settings detayları
- project views
- remove project
- create sonrası detaylı project permission yönetimi
- create ekranında UI ve API ortak çekirdek davranışı dışında kalan API-only alanlar
    - `description`
    - `identifier`
    - `owner_id`
    - `start_date`
    - `end_date`
    - `email`

### Scope Decision
Bu alt modülde yalnızca **UI ve API’nin ortak çekirdek create davranışı** ele alınır. API’de mevcut olan ek proje alanları ayrı kapsam olarak değerlendirilmelidir.

---

## 3. Ürün Davranışı / İş Kuralları Özeti

### BR-CP-01 — Project type modeli
Kanboard’da iki temel proje tipi vardır:
- **Team Project**: user/group management olan çok kullanıcılı model
- **Personal Project**: tek kullanıcıya ait, user management olmayan model

### BR-CP-02 — Team project create yetkisi
Sadece **Administrator** ve **Application Manager** team project oluşturabilir.

### BR-CP-03 — Personal project create yetkisi
**Authenticated user** personal project oluşturabilir.

### BR-CP-04 — Personal project erişim modeli
Personal project’te user management yoktur. Erişim yalnızca **owner** ve **administrator** tarafındadır.

### BR-CP-05 — Başarılı create sonucu
Başarılı create işleminden sonra proje kalıcı olarak oluşmalı, ilgili kullanıcı akışında görünmeli ve API ile doğrulanabilir olmalıdır.

### BR-CP-06 — Negatif create sonucu
Invalid veya unauthorized create denemesi sonrasında sistemde unintended proje kaydı oluşmamalıdır.

### BR-CP-07 — API authorization yaklaşımı
RBAC ve authorization doğrulamalarında **User API** referans alınmalıdır. Application API tüm prosedürlere erişebildiği için authorization doğrulaması için uygun referans değildir.

### BR-CP-08 — API doğrulama yöntemi
Create sonrası persistence doğrulamasında `createProject`, `getProjectById` ve `getProjectByName` gibi proje prosedürleri kullanılabilir.

### BR-CP-09 — Dokümanda açık olmayan alanlar
Project name duplicate davranışı, kesin max/min boundary ve bazı format detayları resmi dokümanda açık ürün kuralı olarak tanımlanmamıştır. Bu nedenle bu alanlar test tasarımında **observation-driven** ele alınır; doğrulanır ama sessiz varsayım yapılmaz.

---

## 4. Entry Criteria

- Kanboard test ortamı erişilebilir durumda olmalı.
- Minimum test kullanıcıları hazır olmalı:
    - `U_ADMIN`
    - `U_MANAGER`
    - `U_USER`
- UI login ve User API credentials doğrulanmış olmalı.
- Test isimleri için benzersiz proje adı üretim stratejisi hazır olmalı.
- Gerekli durumlarda API ile setup ve cleanup yapılabilir olmalı.
- Ortamda önceki koşumlardan kalan kirli veri senaryoları bozmayacak seviyede kontrol altına alınmış olmalı.

---

## 5. Exit Criteria

- Bu modül için tanımlanan kritik create akışlarında blocker/kritik defect kalmamış olmalı.
- Team vs personal create authorization davranışı UI ve User API tarafında tutarlı olmalı.
- Validation negatiflerinde unintended project creation gözlenmemeli.
- Create sonrası görünürlük ve persistence doğrulamalarında kritik veri tutarsızlığı bulunmamalı.

---

## 6. Kısa Test Data Yaklaşımı

### Veri Sınıfları
- **valid**: geçerli ve benzersiz proje isimleri
- **invalid**: boş, whitespace-only veya beklenmeyen format denemeleri
- **boundary**: kısa/uzun isim varyasyonları
- **role-based**: `U_ADMIN`, `U_MANAGER`, `U_USER`
- **seeded data**: rol seti ve API erişim bilgileri
- **disposable data**: test sonunda silinecek veya resetlenecek proje kayıtları

### Veri Yönetim Prensibi
- Proje isimleri koşum bazlı benzersiz üretilmelidir.
- Pozitif create senaryoları disposable data üretir; cleanup notu zorunludur.
- Authorization ve validation negatiflerinde ana assertion, **kayıt oluşmaması** olmalıdır.
- Senaryolar mantıksal olarak ilişkili olabilir; ancak execution bağımlılığı mümkün olduğunca API setup veya seeded data ile çözülmelidir.

---

## 7. Scenario List

### PM-CP-001
- **Scenario ID:** PM-CP-001
- **Scenario Name:** Yetkili application role ile team project başarıyla oluşturulabilmeli
- **Objective:** Team project create akışının yetkili roller için çalıştığını doğrulamak.
- **Risk / Focus Area:** Functional correctness, authorization, create entry point behavior
- **Preconditions:** `U_ADMIN` ve `U_MANAGER` erişilebilir olmalı; create ekranına erişim sağlanmalı.
- **Priority:** Critical
- **Suggested Layer:** UI
- **Requirement / Behavior Reference:** BR-CP-01, BR-CP-02, BR-CP-05
- **Dependency Note:** Foundation senaryodur. Sonraki visibility ve consistency senaryoları için referans davranış sağlar; ancak yürütmede doğrudan başka test çıktısına bağımlı olmamalıdır.

---

### PM-CP-002
- **Scenario ID:** PM-CP-002
- **Scenario Name:** Authenticated user personal project başarıyla oluşturulabilmeli
- **Objective:** Personal project create akışının kullanıcı tipi fark etmeksizin izinli kapsamda çalıştığını doğrulamak.
- **Risk / Focus Area:** Functional correctness, authorization, project type behavior
- **Preconditions:** En az bir `U_USER` hesabı aktif olmalı; personal project create entry point erişilebilir olmalı.
- **Priority:** Critical
- **Suggested Layer:** UI
- **Requirement / Behavior Reference:** BR-CP-01, BR-CP-03, BR-CP-05
- **Dependency Note:** Foundation senaryodur. Access/visibility senaryoları için mantıksal ön koşul sağlar; otomasyonda disposable project ile izole çalıştırılmalıdır.

---

### PM-CP-003
- **Scenario ID:** PM-CP-003
- **Scenario Name:** Başarılı create sonrası kullanıcı doğru sonuca yönlendirilmeli ve oluşturulan kayıt görünür olmalı
- **Objective:** Başarılı create işleminden sonra UI confirmation, navigation ve created record visibility davranışını doğrulamak.
- **Risk / Focus Area:** UX-critical flow, persistence visibility, navigation consistency
- **Preconditions:** Yetkili kullanıcı ile geçerli create verisi hazır olmalı.
- **Priority:** High
- **Suggested Layer:** UI
- **Requirement / Behavior Reference:** BR-CP-05
- **Dependency Note:** PM-CP-001 veya PM-CP-002 ile aynı veri mantığı kullanılabilir; fakat yürütmede proje setup’ı testin kendi içinde yapılmalıdır.

---

### PM-CP-004
- **Scenario ID:** PM-CP-004
- **Scenario Name:** Create ekranında rol bazlı proje tipi seçenekleri matrix mantığıyla doğrulanmalı
- **Objective:** Farklı application role’lerde hangi create seçeneğinin göründüğü veya erişilebilir olduğu davranışı tek senaryoda matrix yaklaşımıyla kontrol etmek.
- **Risk / Focus Area:** Authorization surface, incorrect option exposure, UI role-based behavior
- **Preconditions:** `U_ADMIN`, `U_MANAGER`, `U_USER` hesapları hazır olmalı.
- **Priority:** High
- **Suggested Layer:** UI
- **Requirement / Behavior Reference:** BR-CP-02, BR-CP-03
- **Dependency Note:** Bağımsız yürütülebilir. Sonraki unauthorized create senaryoları için görsel/akış ön bilgisi sağlar.

---

### PM-CP-005
- **Scenario ID:** PM-CP-005
- **Scenario Name:** Yetkisiz user team project oluşturamamalı
- **Objective:** Team project create yetkisinin `U_USER` için engellendiğini doğrulamak.
- **Risk / Focus Area:** Authorization enforcement, privilege escalation
- **Preconditions:** `U_USER` hesabı aktif olmalı; create ekranına veya ilgili create yoluna erişim denenebilir olmalı.
- **Priority:** Critical
- **Suggested Layer:** UI
- **Requirement / Behavior Reference:** BR-CP-02, BR-CP-06
- **Dependency Note:** PM-CP-004 ile mantıksal bağlantılıdır. Ancak burada asıl assertion görünürlük değil, create attempt sonucunun engellenmesi ve kayıt oluşmamasıdır.

---

### PM-CP-006
- **Scenario ID:** PM-CP-006
- **Scenario Name:** User API üzerinden unauthorized team project create isteği reddedilmeli
- **Objective:** User API tarafında authorization kuralının UI ile tutarlı şekilde uygulandığını doğrulamak.
- **Risk / Focus Area:** API authorization, backend rule consistency, privilege escalation
- **Preconditions:** `U_USER` için User API credentials hazır olmalı.
- **Priority:** Critical
- **Suggested Layer:** API
- **Requirement / Behavior Reference:** BR-CP-02, BR-CP-06, BR-CP-07
- **Dependency Note:** PM-CP-005’in API katmanı eşleniğidir; aynı iş kuralını farklı katmanda doğrular. Test verisi tamamen bağımsız tutulmalıdır.

---

### PM-CP-007
- **Scenario ID:** PM-CP-007
- **Scenario Name:** Project name boş bırakıldığında create engellenmeli
- **Objective:** Required field validation’ın create akışını doğru şekilde durdurduğunu doğrulamak.
- **Risk / Focus Area:** Required validation, invalid submission handling
- **Preconditions:** Geçerli create ekranı açık olmalı.
- **Priority:** Critical
- **Suggested Layer:** UI
- **Requirement / Behavior Reference:** BR-CP-06
- **Dependency Note:** Bağımsızdır. Cleanup gerektirmez; ana beklenti kayıt oluşmamasıdır.

---

### PM-CP-008
- **Scenario ID:** PM-CP-008
- **Scenario Name:** Whitespace-only veya trim gerektiren project name girdileri tutarlı ele alınmalı
- **Objective:** Boş gibi görünen ama teknik olarak karakter içeren girdilerde validation davranışını doğrulamak.
- **Risk / Focus Area:** Input sanitization, weak validation, data integrity
- **Preconditions:** Geçerli create ekranı açık olmalı; whitespace varyasyonları hazır olmalı.
- **Priority:** High
- **Suggested Layer:** UI
- **Requirement / Behavior Reference:** BR-CP-06, BR-CP-09
- **Dependency Note:** PM-CP-007 ile aynı validation alanındadır; ayrı risk olması sebebiyle ayrıştırılmıştır. Boundary ve format senaryoları ile reusable data builder paylaşabilir.

---

### PM-CP-009
- **Scenario ID:** PM-CP-009
- **Scenario Name:** Invalid create denemesinde unintended project record oluşmamalı
- **Objective:** Negatif validation sonrasında sistemin kirlenmediğini UI ve/veya API ile doğrulamak.
- **Risk / Focus Area:** No side effect, data integrity, false positive persistence
- **Preconditions:** Negatif create verisi hazır olmalı; proje sorgulama yöntemi belirlenmiş olmalı.
- **Priority:** Critical
- **Suggested Layer:** Hybrid
- **Requirement / Behavior Reference:** BR-CP-06, BR-CP-08
- **Dependency Note:** PM-CP-007 ve PM-CP-008’in sonuç odaklı teknik doğrulamasıdır. Kendi negatif verisini üretmeli, başka testin çıktısını kullanmamalıdır.

---

### PM-CP-010
- **Scenario ID:** PM-CP-010
- **Scenario Name:** Project name boundary ve format davranışı observation-driven şekilde doğrulanmalı
- **Objective:** Dokümanda açık olmayan boundary/format alanlarında gerçek ürün davranışını kontrollü şekilde doğrulamak.
- **Risk / Focus Area:** Underspecified validation, environment-specific behavior, future maintenance risk
- **Preconditions:** Boundary veri seti hazırlanmış olmalı.
- **Priority:** Medium
- **Suggested Layer:** UI
- **Requirement / Behavior Reference:** BR-CP-09
- **Dependency Note:** PM-CP-007/008’den sonra çalıştırılması analiz kolaylığı sağlar; fakat teknik bağımlılık içermez.

---

### PM-CP-011
- **Scenario ID:** PM-CP-011
- **Scenario Name:** Personal project oluşturulduğunda personal access modeliyle başlamalı
- **Objective:** Personal project create sonrası erişim modelinin doğru yansıdığını doğrulamak.
- **Risk / Focus Area:** Access isolation, incorrect exposure, product type consistency
- **Preconditions:** `U_USER` veya izinli başka kullanıcı ile yeni bir personal project oluşturulabilir olmalı.
- **Priority:** Critical
- **Suggested Layer:** Hybrid
- **Requirement / Behavior Reference:** BR-CP-01, BR-CP-03, BR-CP-04, BR-CP-05
- **Dependency Note:** Mantıksal olarak PM-CP-002’ye bağlıdır. Otomasyonda gerekli proje setup’ı aynı test içinde veya API fixture ile oluşturulmalıdır.

---

### PM-CP-012
- **Scenario ID:** PM-CP-012
- **Scenario Name:** Team project oluşturulduğunda shared/team modelini destekleyen başlangıç davranışı göstermeli
- **Objective:** Team project create sonrası bunun personal project gibi davranmadığını ve team modeline uygun başlangıç sunduğunu doğrulamak.
- **Risk / Focus Area:** Project type consistency, access model mismatch, incorrect post-create behavior
- **Preconditions:** `U_ADMIN` veya `U_MANAGER` ile yeni bir team project oluşturulabilir olmalı.
- **Priority:** High
- **Suggested Layer:** UI
- **Requirement / Behavior Reference:** BR-CP-01, BR-CP-02, BR-CP-05
- **Dependency Note:** Mantıksal olarak PM-CP-001’e bağlıdır. Ayrı disposable project kullanılmalıdır.

---

### PM-CP-013
- **Scenario ID:** PM-CP-013
- **Scenario Name:** UI üzerinden başarıyla oluşturulan proje User API ile doğrulanabilmeli
- **Objective:** UI create sonucunun backend persistence ile tutarlı olduğunu doğrulamak.
- **Risk / Focus Area:** Cross-layer consistency, UI false success, backend mismatch
- **Preconditions:** UI üzerinden başarılı create yapılabilecek kullanıcı ve benzersiz proje adı hazır olmalı; User API erişimi bulunmalı.
- **Priority:** Critical
- **Suggested Layer:** Hybrid
- **Requirement / Behavior Reference:** BR-CP-05, BR-CP-07, BR-CP-08
- **Dependency Note:** Başarılı create setup’ı aynı senaryoda yapılmalıdır. Başka senaryonun ürettiği projeyi kullanmak önerilmez.

---

### PM-CP-014
- **Scenario ID:** PM-CP-014
- **Scenario Name:** User API ile oluşturulan proje UI tarafında doğru şekilde görülebilmeli
- **Objective:** API create akışının UI görünürlüğü ve temel proje bilgileriyle uyumlu olduğunu doğrulamak.
- **Risk / Focus Area:** Cross-layer consistency, API-to-UI propagation, persistence visibility
- **Preconditions:** Yetkili User API credentials hazır olmalı; API ile proje oluşturulabilir olmalı.
- **Priority:** High
- **Suggested Layer:** Hybrid
- **Requirement / Behavior Reference:** BR-CP-05, BR-CP-07, BR-CP-08
- **Dependency Note:** PM-CP-013 ile aynı consistency alanındadır ancak ters yönlü doğrulama yaptığı için ayrı risk olarak tutulur. Cleanup zorunludur.

---

## 8. Decision / Observation Notes

1. **Copy behavior bilinçli olarak kapsam dışı bırakıldı.** Çünkü create modülünü gereksiz genişletiyor ve source project dependency oluşturuyor.
2. **Authorization doğrulamasında User API kullanılmalı.** Application API, permission check yapmadığı için RBAC referansı olarak kullanılmamalıdır.
3. **Duplicate project name** davranışı resmi create dokümanında açık kural olarak tanımlanmadığı için bu sürümde ayrı standalone scenario yapılmadı. Gerekirse PM-CP-010 altında observation-driven kapsam olarak ele alınabilir.
4. **API-only proje alanları** bu modülde senaryolaştırılmadı. Bunlar ayrı bir “Create Project API Extended Fields” alt modülünde ele alınmalıdır.

---

## 9. Önerilen Execution Sırası

Önerilen mantıksal sıra:
1. Foundation create success
    - PM-CP-001
    - PM-CP-002
    - PM-CP-003
2. Authorization
    - PM-CP-004
    - PM-CP-005
    - PM-CP-006
3. Validation / no side effect
    - PM-CP-007
    - PM-CP-008
    - PM-CP-009
    - PM-CP-010
4. Access / visibility outcome
    - PM-CP-011
    - PM-CP-012
5. Cross-layer consistency
    - PM-CP-013
    - PM-CP-014

Not: Bu sıra mantıksal analiz sırasıdır. Otomasyon koşumunda testler mümkün olduğunca bağımsız yürütülmelidir.

---

## 10. Automation Notes

- **UI uygun adaylar:** PM-CP-001, PM-CP-002, PM-CP-003, PM-CP-004, PM-CP-005, PM-CP-007, PM-CP-008, PM-CP-010, PM-CP-012
- **API uygun adaylar:** PM-CP-006
- **Hybrid uygun adaylar:** PM-CP-009, PM-CP-011, PM-CP-013, PM-CP-014

### Setup / Cleanup Prensibi
- Pozitif create senaryolarında proje disposable olarak üretilmeli ve koşum sonunda silinmelidir.
- Validation ve unauthorized create senaryolarında cleanup yerine **record not created** assertion’ı yeterli olabilir.
- Visibility ve consistency senaryolarında reusable fixture yerine test içi create + test içi cleanup daha güvenlidir.

---

## 11. Test Design Notes

### Bu modüldeki en kritik risk alanları
- Yetkisiz kullanıcının team project oluşturabilmesi
- Başarısız create denemesinde unintended kayıt oluşması
- Personal vs team access modelinin yanlış başlaması
- UI ile backend arasında create sonucu tutarsızlığı

### Neden bu senaryolar seçildi
Bu set, create modülünün gerçekten kritik davranışlarını kapsar: başarılı oluşturma, authorization, validation, visibility ve cross-layer consistency. Copy, edit ve geniş permission yönetimi gibi alanlar bilinçli olarak dışarıda bırakılmıştır.

### Nerede matrix / data-driven yaklaşım kullanıldı
- PM-CP-001: yetkili team create rolleri
- PM-CP-002: personal project create için izinli kullanıcı kapsamı
- PM-CP-004: create option ve proje tipi görünürlüğü
- PM-CP-010: boundary/format veri varyasyonları

### Hangi senaryolar otomasyon için en güçlü adaylar
- PM-CP-001, PM-CP-002, PM-CP-005, PM-CP-006, PM-CP-007, PM-CP-009, PM-CP-013, PM-CP-014

### Hangi senaryolar seeded data ister
- PM-CP-004, PM-CP-005, PM-CP-006 için rol seti
- PM-CP-011, PM-CP-012 için çok kullanıcılı doğrulama gereken durumlarda ek kullanıcı seti

### Hangi senaryolar cleanup gerektirir
- Pozitif create içeren tüm senaryolar: PM-CP-001, PM-CP-002, PM-CP-003, PM-CP-011, PM-CP-012, PM-CP-013, PM-CP-014

### Olası flaky riskler
- UI sonrası liste güncellenmesinde gecikme
- Aynı isimli proje verisinin önceki koşumlardan kalması
- API ve UI doğrulama arasında zamanlama farkı
- Rol setinin ortamda yanlış konfigüre edilmiş olması
