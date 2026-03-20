# Project Management - Create Project - Test Cases

## 1. Document Purpose

Bu doküman, **Kanboard > Project Management > Create Project** alt modülü için birleşik ve otomasyona uygun test case setini içerir.

Bu yapı şu hedeflerle hazırlanmıştır:
- senaryo dokümanındaki iş kurallarını otomasyona çevrilebilir case’lere dönüştürmek,
- UI, API ve Hybrid kapsamını net ayırmak,
- role-based tekrarları mümkün olduğunca matrix / data-driven yapı ile azaltmak,
- cleanup, reusable setup ve traceability ihtiyacını açık tutmak,
- sürdürülebilir, tekrar koşulabilir ve bakım maliyeti düşük bir case seti sunmak.

Bu dokümanda **manual-only case** bulunmaz. Dokümanda açık olmayan ürün davranışları için gerektiğinde kontrollü assertion ve karar notu yaklaşımı kullanılır.

---

## 2. Scope Alignment

### In Scope
- Team project create
- Personal project create
- Create ekranı temel validation davranışları
- Application role bazlı authorization
- Create sonrası görünürlük ve temel persistence doğrulaması
- UI, API ve Hybrid otomasyon adayları
- Invalid / unauthorized create sonrası no-side-effect kontrolü

### Out of Scope
- Existing project üzerinden copy / duplicate create davranışı
- Edit project
- Default settings detayları
- Project views
- Remove project
- Create modülünün ortak çekirdek davranışı dışında kalan API-only alanlar (`description`, `identifier`, `owner_id`, `start_date`, `end_date`, `email`)

---

## 3. Execution Order Recommendation

Önerilen koşum sırası:

1. Authorization ve validation negatifleri
2. Baseline success create akışları
3. Create sonrası visibility / navigation doğrulamaları
4. Hybrid persistence ve cross-layer consistency doğrulamaları
5. Observation-sensitive extended coverage doğrulamaları

Bu sıra:
- kirli veri üretme riskini azaltır,
- setup ve cleanup kontrolünü kolaylaştırır,
- framework oturmadan düşük değerli coverage’a gitmeyi engeller.

---

## 4. Common Test Data, Setup and Cleanup Strategy

### Seeded Users
- `U_ADMIN`
- `U_MANAGER`
- `U_USER`

### Disposable Naming Pattern
- `QA_PM_CP_<purpose>_<timestamp>`

### Reusable Helpers
- `loginAs(roleKey)`
- `openCreateProjectPage()`
- `openCreatePersonalProjectPage()`
- `generateUniqueProjectName(prefix)`
- `createProjectViaUserApi(name, type, credentials)`
- `getProjectByNameViaUserApi(name, credentials)`
- `getProjectByIdViaUserApi(projectId, credentials)`
- `deleteProjectViaApi(projectId)`
- `assertProjectAbsentViaApi(name, credentials)`

### Data Classes Used
- `valid`
- `invalid`
- `boundary`
- `role-based`
- `seeded data`
- `disposable data`

### Shared Principles
- Pozitif create case’lerinde benzersiz disposable project adı kullanılmalıdır.
- Negatif case’lerde de benzersiz test adı kullanılmalı ki eski veri false positive üretmesin.
- Negatif create case’lerinde ana teknik doğrulama **record not created** olmalıdır.
- Senaryolar mantıksal olarak bağlantılı olabilir; fakat execution bağımlılığı mümkün olduğunca API setup veya seeded data ile çözülmelidir.

---

## 5. Detailed Test Cases

---

## TC-PM-CP-001
- **Test Case ID:** TC-PM-CP-001
- **Related Scenario ID:** PM-CP-001
- **Test Case Title:** Authorized application role matrix ile team project başarıyla oluşturulabilmeli
- **Purpose:** Team project create davranışının yetkili application rollerde doğru çalıştığını tek data-driven yapı altında doğrulamak.
- **Priority:** Critical
- **Coverage Type:** Core Regression
- **Layer:** UI
- **Preconditions:**
  - `U_ADMIN` ve `U_MANAGER` hesapları erişilebilir olmalı.
  - Kullanıcılar team project create ekranına ulaşabilmeli.
  - Disposable project name üretimi hazır olmalı.
- **Test Data:**
  - Role Matrix:
    - `U_ADMIN`
    - `U_MANAGER`
  - Project Type: `Team`
  - Project Name Pattern: `QA_PM_CP_TEAM_AUTH_<role>_<timestamp>`
- **Steps:**
  1. İlk veri seti ile giriş yap.
  2. Team project create akışını aç.
  3. Benzersiz ve geçerli bir project name gir.
  4. Team project olarak submit et.
  5. Submit sonrası başarı sonucu veya create ile tutarlı redirect davranışını gözlemle.
  6. Açılan ekranda proje adını doğrula.
  7. Aynı akışı ikinci veri seti ile tekrar et.
- **Expected Results:**
  - `U_ADMIN` ve `U_MANAGER` team project create akışına erişebilmelidir.
  - Submit sonrası işlem hata vermeden tamamlanmalıdır.
  - Kullanıcı yeni oluşturulan projenin sayfasına veya bunu açıkça gösteren tutarlı bir ekrana yönlendirilmelidir.
  - Oluşturulan proje adı girilen veri ile eşleşmelidir.
  - Her veri seti kendi disposable projesini bağımsız olarak oluşturabilmelidir.
- **Postconditions / Cleanup:**
  - Her iki veri seti ile oluşturulan disposable projeler silinmelidir.
- **Dependency:**
  - Seeded role accounts gerekir.
  - Cleanup helper önerilir.
- **Automation Candidate Note:**
  - Güçlü UI smoke ve regression adayıdır.
  - TestNG `@DataProvider` ile rol bazlı tekrar azaltılmalıdır.
- **Traceability Note:**
  - BR-CP-01, BR-CP-02, BR-CP-05
  - PM-CP-001

---

## TC-PM-CP-002
- **Test Case ID:** TC-PM-CP-002
- **Related Scenario ID:** PM-CP-002
- **Test Case Title:** Authenticated standard user personal project başarıyla oluşturabilmeli
- **Purpose:** Personal project create davranışının standart kullanıcı için çalıştığını doğrulamak.
- **Priority:** Critical
- **Coverage Type:** Core Regression
- **Layer:** UI
- **Preconditions:**
  - `U_USER` aktif olmalı.
  - Personal project create giriş noktası erişilebilir olmalı.
  - Benzersiz project name üretimi hazır olmalı.
- **Test Data:**
  - User Role: `U_USER`
  - Project Type: `Personal`
  - Project Name: `QA_PM_CP_PERSONAL_USER_<timestamp>`
- **Steps:**
  1. `U_USER` ile giriş yap.
  2. Personal project create akışını aç.
  3. Benzersiz ve geçerli project name gir.
  4. Formu submit et.
  5. Başarı sonucu veya create ile tutarlı landing davranışını gözlemle.
  6. Açılan ekranda proje adını doğrula.
  7. Kullanıcının proje listesi, dashboard veya güvenilir bir UI noktasında projenin görünürlüğünü kontrol et.
- **Expected Results:**
  - Standart kullanıcı personal project create akışına erişebilmelidir.
  - İşlem başarıyla tamamlanmalıdır.
  - Kullanıcı yeni oluşturulan personal project'i kendi perspektifinde görebilmelidir.
  - Proje adı girilen veriyle birebir eşleşmelidir.
  - Oluşan kayıt personal project create akışıyla tutarlı UI state göstermelidir.
- **Postconditions / Cleanup:**
  - Oluşturulan personal project silinmelidir.
- **Dependency:**
  - Seeded `U_USER` gerekir.
- **Automation Candidate Note:**
  - İlk dalga UI smoke için güçlü adaydır.
  - Personal project akışı için ayrı page object method'u tutulması bakım kolaylığı sağlar.
- **Traceability Note:**
  - BR-CP-01, BR-CP-03, BR-CP-05
  - PM-CP-002

---

## TC-PM-CP-003
- **Test Case ID:** TC-PM-CP-003
- **Related Scenario ID:** PM-CP-003
- **Test Case Title:** Başarılı create sonrası kullanıcı doğru sonuca yönlendirilmeli ve kayıt görünür olmalı
- **Purpose:** Başarılı create işleminden sonra UI feedback, redirect ve created record visibility davranışını doğrulamak.
- **Priority:** High
- **Coverage Type:** Core Regression
- **Layer:** UI
- **Preconditions:**
  - Geçerli create yapabilecek tek bir kullanıcı hazır olmalı.
  - Benzersiz geçerli project name hazır olmalı.
- **Test Data:**
  - User Role: `U_ADMIN`
  - Project Type: `Team`
  - Project Name: `QA_PM_CP_VISIBILITY_<timestamp>`
- **Steps:**
  1. Geçerli kullanıcı ile giriş yap.
  2. İlgili create akışını aç.
  3. Geçerli project name gir ve formu submit et.
  4. Submit sonrası gösterilen başarı sonucunu gözlemle.
  5. Redirect edilen ekranı doğrula.
  6. Yeni proje adının hedef ekranda göründüğünü doğrula.
  7. Dashboard, listing veya current project header üzerinden kayıt görünürlüğünü tekrar doğrula.
- **Expected Results:**
  - Kullanıcı başarısızlık mesajı görmemelidir.
  - Kullanıcı başarıyı gösteren net bir UI sonucu almalıdır.
  - Redirect hedefi create sonrası akışla tutarlı olmalıdır.
  - Oluşturulan kayıt en az bir güvenilir UI noktada görünmelidir.
  - Görünen proje adı submit edilen veri ile eşleşmelidir.
- **Postconditions / Cleanup:**
  - Oluşturulan proje cleanup ile silinmelidir.
- **Dependency:**
  - Test içinde create yapılır; başka testin bıraktığı veri kullanılmaz.
- **Automation Candidate Note:**
  - UI navigation ve visibility doğrulaması için güçlü adaydır.
  - Bu case, create yetki doğrulamasından çok create sonrası kalıcı UI state'i hedeflemelidir.
- **Traceability Note:**
  - BR-CP-05, BR-CP-07
  - PM-CP-003

---

## TC-PM-CP-004
- **Test Case ID:** TC-PM-CP-004
- **Related Scenario ID:** PM-CP-004
- **Test Case Title:** Role-based create option exposure matrix UI seviyesinde doğrulanmalı
- **Purpose:** Farklı application role’lerde create giriş noktalarının görünürlüğünü ve UI seviyesindeki erişilebilir surface davranışını matrix yaklaşımıyla doğrulamak.
- **Priority:** High
- **Coverage Type:** Extended Coverage
- **Layer:** UI
- **Preconditions:**
  - `U_ADMIN`, `U_MANAGER`, `U_USER` hesapları hazır olmalı.
- **Test Data:**
  - Role Matrix:
    - `U_ADMIN` → Team create görünür, Personal create görünür
    - `U_MANAGER` → Team create görünür, Personal create görünür
    - `U_USER` → Team create görünmez, Personal create görünür
- **Steps:**
  1. `U_ADMIN` ile giriş yap.
  2. Dashboard veya ilgili create surface üzerinde görünen create giriş noktalarını gözlemle.
  3. Team project create ve personal project create seçeneklerinin görünürlük durumunu kaydet.
  4. `U_MANAGER` ile aynı kontrolleri tekrarla.
  5. `U_USER` ile aynı kontrolleri tekrarla.
- **Expected Results:**
  - `U_ADMIN` için team project create seçeneği görünür olmalıdır.
  - `U_ADMIN` için personal project create seçeneği görünür olmalıdır.
  - `U_MANAGER` için team project create seçeneği görünür olmalıdır.
  - `U_MANAGER` için personal project create seçeneği görünür olmalıdır.
  - `U_USER` için personal project create seçeneği görünür olmalıdır.
  - `U_USER` için team project create seçeneği görünmemelidir.
  - UI surface role kuralıyla çelişmemelidir.
- **Postconditions / Cleanup:**
  - Veri üretilmezse cleanup gerekmez.
- **Dependency:**
  - Seeded role accounts gerekir.
  - Bu case UI surface/visibility doğrulamasıdır.
  - Team project create için direct access veya unauthorized create enforcement doğrulaması ayrı authorization testlerinde ele alınmalıdır.
- **Automation Candidate Note:**
  - Data-driven UI coverage için uygundur.
  - Görünürlük assertion’ları ortak yardımcı metotlarda tutulabilir.
- **Traceability Note:**
  - BR-CP-02, BR-CP-03
  - PM-CP-004

---

## TC-PM-CP-005
- **Test Case ID:** TC-PM-CP-005
- **Related Scenario ID:** PM-CP-005
- **Test Case Title:** Unauthorized user UI üzerinden team project oluşturamamalı
- **Purpose:** Standart kullanıcının UI üzerinden team project create edemediğini ve privilege escalation oluşmadığını doğrulamak.
- **Priority:** Critical
- **Coverage Type:** Core Regression
- **Layer:** UI
- **Preconditions:**
    - `U_USER` hazır olmalı.
    - Team create yolunu denemeye uygun test ortamı bulunmalı.
- **Test Data:**
    - User Role: `U_USER`
    - Project Name: `QA_PM_CP_UNAUTH_UI_<timestamp>`
    - Project Type: `Team`
- **Steps:**
    1. `U_USER` ile giriş yap.
    2. Team project create yolunu UI üzerinden dene.
    3. Erişim varsa project name girip submit etmeye çalış.
    4. Erişim yoksa bu davranışı kaydet.
    5. Attempt sonrası proje oluşup oluşmadığını UI seviyesinde kontrol et.
- **Expected Results:**
    - `U_USER` team project create’i başarıyla tamamlayamamalıdır.
    - UI erişimi ya en başta engellemeli ya da submit seviyesinde işlemi reddetmelidir.
    - Kullanıcı team project sayfasına başarılı create sonucu ile yönlendirilmemelidir.
    - Başarılı create’e karşılık gelen kalıcı kayıt görünmemelidir.
- **Postconditions / Cleanup:**
    - Kayıt oluşmamalıdır; yine de absence check yapılmalıdır.
- **Dependency:**
    - PM-CP-004 ile mantıksal bağlantılıdır fakat kendi başına yürütülmelidir.
- **Automation Candidate Note:**
    - Ana negatif authorization case’idir.
    - Sadece hata mesajına değil kalıcı kayıt yokluğuna da bakılmalıdır.
- **Traceability Note:**
    - BR-CP-02, BR-CP-06
    - PM-CP-005

---

## TC-PM-CP-006
- **Test Case ID:** TC-PM-CP-006
- **Related Scenario ID:** PM-CP-006
- **Test Case Title:** Unauthorized user User API üzerinden yetkisiz project create isteğinde başarılı sonuç alamamalı
- **Purpose:** Backend seviyesinde authorization enforcement doğrulamasını yapmak.
- **Priority:** Critical
- **Coverage Type:** Core Regression
- **Layer:** API
- **Preconditions:**
  - `U_USER` için User API credentials hazır olmalı.
  - JSON-RPC endpoint erişilebilir olmalı.
- **Test Data:**
  - API User: `U_USER`
  - Project Name: `QA_PM_CP_UNAUTH_API_<timestamp>`
  - Procedure: `createProject`
- **Steps:**
  1. `U_USER` credentials ile User API isteği hazırla.
  2. Yetkisiz create amacıyla `createProject` isteğini gönder.
  3. Dönen API sonucunu kaydet.
  4. Aynı isim için `getProjectByName` veya eşdeğer read çağrısı yap.
  5. Kalıcı kayıt oluşup oluşmadığını doğrula.
- **Expected Results:**
  - Yetkisiz kullanıcı create isteğinde başarılı sonuç alamamalıdır.
  - API cevabı authorization failure, rejected result veya create başarısızlığı ile tutarlı olmalıdır.
  - Aynı isimle kalıcı proje kaydı oluşmamalıdır.
  - Backend tarafında privilege escalation olmamalıdır.
- **Postconditions / Cleanup:**
  - Kayıt oluşmaması beklenir; oluşursa defect + cleanup gerekir.
- **Dependency:**
  - API read helper gerekir.
- **Automation Candidate Note:**
  - API authorization regression için güçlü adaydır.
  - Assertion yalnız response status'a değil kalıcı veri yokluğuna da dayanmalıdır.
- **Traceability Note:**
  - BR-CP-02, BR-CP-06, BR-CP-08
  - PM-CP-006

---

## TC-PM-CP-007
- **Test Case ID:** TC-PM-CP-007
- **Related Scenario ID:** PM-CP-007
- **Test Case Title:** Project name boş bırakıldığında create engellenmeli
- **Purpose:** Required field validation’ın boş name girişinde create akışını durdurduğunu doğrulamak.
- **Priority:** Critical
- **Coverage Type:** Core Regression
- **Layer:** UI
- **Preconditions:**
    - Geçerli create ekranı erişilebilir olmalı.
- **Test Data:**
    - Project Name: `""`
    - Project Type: geçerli bir create tipi
- **Steps:**
    1. Create ekranını aç.
    2. Project name alanını boş bırak.
    3. Formu submit et.
    4. Validation geri bildirimini gözlemle.
    5. Başarılı create sonucuna ait UI state oluşup oluşmadığını kontrol et.
- **Expected Results:**
    - Form submit edilse bile create tamamlanmamalıdır.
    - Kullanıcı anlamlı bir validation geri bildirimi almalıdır.
    - Başarılı create redirect’i gerçekleşmemelidir.
    - Boş isimle kalıcı kayıt oluşmamalıdır.
- **Postconditions / Cleanup:**
    - Cleanup gerekmez.
- **Dependency:**
    - Yok.
- **Automation Candidate Note:**
    - Hızlı ve stabil validation smoke case’idir.
- **Traceability Note:**
    - BR-CP-06
    - PM-CP-007

---

## TC-PM-CP-008
- **Test Case ID:** TC-PM-CP-008
- **Related Scenario ID:** PM-CP-008
- **Test Case Title:** Whitespace-only ve trim-sensitive project name girdileri tutarlı şekilde reddedilmeli veya kontrollü şekilde ele alınmalı
- **Purpose:** Input sanitization ve weak validation risklerini matrix yaklaşımıyla doğrulamak.
- **Priority:** High
- **Coverage Type:** Extended Coverage
- **Layer:** UI
- **Preconditions:**
  - Create ekranı erişilebilir olmalı.
- **Test Data:**
  - Invalid Input Matrix:
    - `" "`
    - `"     "`
    - `"\t"`
    - `"  QA_PM_CP_TRIM_ONLY_<timestamp>  "` *(ürün kabul ederse sanitize edilmiş final değer deterministik olmalı; reddederse kayıt oluşmamalı)*
  - Project Type: geçerli bir create tipi
- **Steps:**
  1. İlk invalid input ile create denemesi yap.
  2. Validation sonucu ve submit sonrası UI state'i kaydet.
  3. Kalan veri setleri için aynı akışı tekrarla.
  4. Trim-sensitive veri setinde kabul varsa oluşan nihai proje adını kontrol et.
- **Expected Results:**
  - Whitespace-only girdiler create'i başarıyla tamamlayamamalıdır.
  - Boş gibi davranması gereken girdiler için kalıcı kayıt oluşmamalıdır.
  - Trim-sensitive giriş kabul ediliyorsa sistem deterministik davranmalı ve proje adı belirsiz veya bozuk biçimde kaydedilmemelidir.
  - Aynı veri seti tekrar koşulduğunda tutarlı sonuç alınmalıdır.
- **Postconditions / Cleanup:**
  - Kayıt oluşursa cleanup yapılmalıdır.
- **Dependency:**
  - Yok.
- **Automation Candidate Note:**
  - Data-driven UI validation için uygundur.
  - Trim-sensitive varyasyon için first-run baseline sonucu sabitlenmelidir.
  - Bu case ilk dalga blocking regression yerine controlled extended suite altında tutulmalıdır.
- **Traceability Note:**
  - BR-CP-06, BR-CP-09
  - PM-CP-008

---

## TC-PM-CP-009
- **Test Case ID:** TC-PM-CP-009
- **Related Scenario ID:** PM-CP-009
- **Test Case Title:** Invalid create denemesi unintended project record bırakmamalı
- **Purpose:** Negatif create denemelerinde sistemin kirlenmediğini hybrid doğrulama ile kontrol etmek.
- **Priority:** Critical
- **Coverage Type:** Core Regression
- **Layer:** Hybrid
- **Preconditions:**
  - UI create ekranı ve User API erişimi hazır olmalı.
  - Benzersiz invalid test adı belirlenmiş olmalı.
- **Test Data:**
  - Invalid Name: `"  QA_PM_CP_NEG_TRIM_<timestamp>  "`
  - Lookup Variants:
    - raw input değeri
    - trimmed value: `QA_PM_CP_NEG_TRIM_<timestamp>`
- **Steps:**
  1. UI üzerinden invalid veya trim-sensitive negatif veri ile create denemesi yap.
  2. UI'nin create'i reddettiğini veya deterministik olmayan başarı üretmediğini doğrula.
  3. Raw input varyasyonu için User API üzerinden proje araması yap.
  4. Trimmed varyasyon için de User API üzerinden proje araması yap.
  5. Beklenmeyen kalıcı kayıt oluşup oluşmadığını doğrula.
- **Expected Results:**
  - UI create'i başarılı tamamlamamalıdır.
  - Raw input veya trimmed varyasyon altında unintended kalıcı kayıt oluşmamalıdır.
  - Negatif deneme sonrasında backend tarafında side effect bırakılmamalıdır.
  - UI sonucu ile API tarafındaki absence sonucu birbiriyle tutarlı olmalıdır.
- **Postconditions / Cleanup:**
  - Kayıt bulunmaması beklenir; bulunursa defect + cleanup gerekir.
- **Dependency:**
  - UI validation ve API lookup yardımcıları gerekir.
- **Automation Candidate Note:**
  - Güçlü hybrid negatif case'tir.
  - Negatif UI assertion ile API absence assertion birlikte tutulmalıdır.
- **Traceability Note:**
  - BR-CP-06, BR-CP-08
  - PM-CP-009

---

## TC-PM-CP-010
- **Test Case ID:** TC-PM-CP-010
- **Related Scenario ID:** PM-CP-010
- **Test Case Title:** Project name boundary ve güvenli format davranışı kontrollü extended coverage ile izlenmeli
- **Purpose:** Dokümanda açık olmayan boundary/format davranışlarını sessiz varsayım yapmadan kontrollü şekilde doğrulamak.
- **Priority:** Medium
- **Coverage Type:** Extended Coverage
- **Layer:** UI
- **Preconditions:**
    - Boundary veri seti hazırlanmış olmalı.
    - Test ortamında ilk baseline gözlemi daha önce alınmış olmalı veya bu test quarantine olarak işaretlenmiş olmalı.
- **Test Data:**
    - Boundary / Format Matrix:
        - kısa ama geçerli aday isim
        - uzun aday isim
        - özel karakter içeren ama güvenli aday isim
        - çok uzun / riskli aday isim
- **Steps:**
    1. Veri setindeki ilk aday ile create denemesi yap.
    2. Sistem davranışını kaydet.
    3. Veri setindeki diğer adaylar için aynı akışı tekrarla.
    4. Kabul edilen girişlerde oluşan proje adını kontrol et.
    5. Reddedilen girişlerde kayıt oluşmadığını doğrula.
- **Expected Results:**
    - Sistem her veri seti için deterministik davranmalıdır.
    - Beklenmeyen server error, broken UI veya sessiz veri bozulması olmamalıdır.
    - Kabul edilen girdilerde proje adı bozuk veya tutarsız saklanmamalıdır.
    - Reddedilen girdilerde kalıcı kayıt oluşmamalıdır.
- **Postconditions / Cleanup:**
    - Kabul edilen disposable kayıtlar silinmelidir.
- **Dependency:**
    - İlk baseline sonucu sabitlenmeden bu test blocking regression olarak kullanılmamalıdır.
- **Automation Candidate Note:**
    - Observation-sensitive extended case’tir.
    - İlk aşamada quarantine veya separate suite altında tutulması daha güvenlidir.
- **Traceability Note:**
    - BR-CP-09
    - PM-CP-010

---

## TC-PM-CP-011
- **Test Case ID:** TC-PM-CP-011
- **Related Scenario ID:** PM-CP-011
- **Test Case Title:** Personal project oluşturulduğunda owner odaklı access modeliyle başlamalı
- **Purpose:** Personal project create sonrası erişim izolasyonunun doğru başladığını doğrulamak.
- **Priority:** Critical
- **Coverage Type:** Extended Coverage
- **Layer:** Hybrid
- **Preconditions:**
  - `U_USER` ile personal project create yapılabilir olmalı.
  - Erişim sonucu karşılaştırması için en az bir farklı standart kullanıcı hazır olmalı.
- **Test Data:**
  - Owner User: `U_USER`
  - Secondary User: `U_USER_2`
  - Project Name: `QA_PM_CP_PERSONAL_ACCESS_<timestamp>`
- **Steps:**
  1. `U_USER` ile personal project oluştur.
  2. Owner olarak proje görünürlüğünü ve erişimini doğrula.
  3. `U_USER_2` perspektifinden aynı projeye erişimi dene veya görünürlüğünü kontrol et.
  4. Mümkünse User API ile owner perspective üzerinden proje kaydını doğrula.
- **Expected Results:**
  - Owner oluşturduğu personal project'i görebilmeli ve erişebilmelidir.
  - Personal project team project gibi geniş erişim modeli göstermemelidir.
  - İkinci standart kullanıcı aynı projeyi normal team project gibi görememeli veya yönetememelidir.
  - Persistence kaydı oluşturulan project ile tutarlı olmalıdır.
- **Postconditions / Cleanup:**
  - Oluşturulan personal project silinmelidir.
- **Dependency:**
  - Çok kullanıcılı doğrulama için seeded users gerekir.
- **Automation Candidate Note:**
  - Hybrid suite için iyi bir orta seviye coverage case'idir.
  - İkinci kullanıcı perspektifi API veya UI ile maliyeti düşük yoldan doğrulanmalıdır.
- **Traceability Note:**
  - BR-CP-01, BR-CP-03, BR-CP-04, BR-CP-05
  - PM-CP-011

---

## TC-PM-CP-012
- **Test Case ID:** TC-PM-CP-012
- **Related Scenario ID:** PM-CP-012
- **Test Case Title:** Team project oluşturulduğunda team modeline uygun temel yönetim yüzeyi göstermeli
- **Purpose:** Team project create sonrası başlangıç davranışının personal modelle karışmadığını doğrulamak.
- **Priority:** High
- **Coverage Type:** Extended Coverage
- **Layer:** UI
- **Preconditions:**
  - `U_ADMIN` veya `U_MANAGER` ile team project create yapılabilir olmalı.
- **Test Data:**
  - Creator Role: `U_ADMIN`
  - Project Name: `QA_PM_CP_TEAM_MODEL_<timestamp>`
- **Steps:**
  1. Yetkili rol ile team project oluştur.
  2. Oluşturulan proje sayfasını aç.
  3. Team project'e ait temel yönetim veya ayar yüzeylerinden en az birini kontrol et.
  4. Personal project'e özgü izole davranışın burada baskın başlangıç modeli olmadığını doğrula.
- **Expected Results:**
  - Team project başarıyla oluşmalıdır.
  - Oluşan proje personal project akışıyla karışmamalıdır.
  - Team modeline uygun en az bir somut yönetim, ayar veya görünürlük yüzeyi bulunmalıdır.
  - Görünen proje state'i create akışıyla tutarlı olmalıdır.
- **Postconditions / Cleanup:**
  - Oluşturulan team project silinmelidir.
- **Dependency:**
  - Foundation team create davranışı gerekir; ancak proje test içinde oluşturulmalıdır.
- **Automation Candidate Note:**
  - UI tarafında type-specific smoke veya targeted extended coverage olarak tutulabilir.
  - Over-assertion yapılmamalı; yalnızca create modülüyle doğrudan ilişkili farklar doğrulanmalıdır.
- **Traceability Note:**
  - BR-CP-01, BR-CP-02, BR-CP-05
  - PM-CP-012

---

## TC-PM-CP-013
- **Test Case ID:** TC-PM-CP-013
- **Related Scenario ID:** PM-CP-013
- **Test Case Title:** UI üzerinden oluşturulan proje User API ile doğrulanabilmeli
- **Purpose:** UI success sonucunun gerçek backend persistence ile tutarlı olduğunu doğrulamak.
- **Priority:** Critical
- **Coverage Type:** Core Regression
- **Layer:** Hybrid
- **Preconditions:**
    - UI create akışı ve User API erişimi çalışır durumda olmalı.
    - Benzersiz disposable project name hazır olmalı.
- **Test Data:**
    - Valid User
    - Project Name: `QA_PM_CP_UI_API_<timestamp>`
    - Project Type: scope’a uygun geçerli tip
- **Steps:**
    1. UI üzerinden geçerli create işlemini tamamla.
    2. UI tarafında proje adını ve başarı sonucunu doğrula.
    3. Aynı project name ile User API üzerinden `getProjectByName` çağrısı yap.
    4. Dönen proje bilgilerini kontrol et.
    5. Gerekirse proje kimliği ile ikinci read doğrulaması yap.
- **Expected Results:**
    - UI başarı gösterdiğinde backend’de ilgili proje kaydı bulunmalıdır.
    - API’den dönen proje adı UI’da girilen veriyle eşleşmelidir.
    - Proje tek ve tutarlı bir kayıt olarak bulunmalıdır.
    - UI false success durumu oluşmamalıdır.
- **Postconditions / Cleanup:**
    - Oluşturulan proje silinmelidir.
- **Dependency:**
    - UI create helper + User API read helper gerekir.
- **Automation Candidate Note:**
    - En güçlü hybrid core regression case’lerinden biridir.
    - UI assertion ve API assertion ayrı helper katmanlarında tutulmalıdır.
- **Traceability Note:**
    - BR-CP-05, BR-CP-07, BR-CP-08
    - PM-CP-013

---

## TC-PM-CP-014
- **Test Case ID:** TC-PM-CP-014
- **Related Scenario ID:** PM-CP-014
- **Test Case Title:** User API ile oluşturulan proje UI tarafında doğru görünmelidir
- **Purpose:** API ile oluşturulan kayıtların UI görünürlüğü ve temel proje bilgileriyle tutarlı olduğunu doğrulamak.
- **Priority:** High
- **Coverage Type:** Extended Coverage
- **Layer:** Hybrid
- **Preconditions:**
    - Yetkili User API credentials hazır olmalı.
    - UI login yapılabilir olmalı.
    - Cleanup helper hazır olmalı.
- **Test Data:**
    - API User: team project için `U_ADMIN` veya `U_MANAGER`, personal project için uygun kullanıcı
    - Project Name: `QA_PM_CP_API_UI_<timestamp>`
- **Steps:**
    1. User API üzerinden geçerli create isteği gönder.
    2. Dönen proje kimliği veya adı ile create başarısını doğrula.
    3. Uygun kullanıcı ile UI’a giriş yap.
    4. Oluşturulan projenin UI listesinde veya hedef ekranda göründüğünü doğrula.
    5. UI’daki proje adının API ile oluşturulan kayıtla eşleştiğini kontrol et.
- **Expected Results:**
    - API ile başarıyla oluşturulan proje UI tarafında bulunabilmelidir.
    - UI’daki temel proje bilgisi API create verisiyle tutarlı olmalıdır.
    - Kayıt görünürlüğü proje tipi ve kullanıcı perspektifi ile çelişmemelidir.
- **Postconditions / Cleanup:**
    - Oluşturulan proje silinmelidir.
- **Dependency:**
    - API create helper, UI listing helper ve uygun kullanıcı setup’ı gerekir.
- **Automation Candidate Note:**
    - API-first hybrid coverage için değerlidir.
    - Framework olgunlaştıktan sonra eklenmesi bakım maliyetini düşürür.
- **Traceability Note:**
    - BR-CP-05, BR-CP-07, BR-CP-08
    - PM-CP-014

---

## 6. Test Design Notes

### Bu modüldeki en kritik risk alanları
- Yetkisiz kullanıcının team project oluşturabilmesi
- Invalid create denemesinde unintended veri oluşması
- UI’da başarı görüldüğü halde backend’de kayıt olmaması
- Personal ve team project davranışlarının karışması

### Neden bu case’ler seçildi
Bu set, create modülünün en kritik davranışlarını şişirmeden kapsar:
- temel success flow,
- authorization enforcement,
- validation,
- access / visibility sonucu,
- cross-layer consistency.

### Nerede matrix / data-driven yaklaşım kullanıldı
- `TC-PM-CP-001` → authorized role matrix
- `TC-PM-CP-004` → role-based option exposure matrix
- `TC-PM-CP-008` → whitespace / trim-sensitive invalid input matrix
- `TC-PM-CP-010` → boundary / format observation matrix

### Hangi case’ler otomasyon için en güçlü aday
İlk dalga için en güçlü adaylar:
- `TC-PM-CP-001`
- `TC-PM-CP-002`
- `TC-PM-CP-005`
- `TC-PM-CP-006`
- `TC-PM-CP-007`
- `TC-PM-CP-009`
- `TC-PM-CP-013`

### Hangi case’ler seeded data ister
- Authorization ve role matrix içeren case’ler
- Access / visibility sonucu için çok kullanıcı gerektiren case’ler
- API / UI hybrid case’ler için güvenilir kullanıcı credential seti

### Hangi case’ler cleanup gerektirir
- Pozitif create yapan tüm case’ler cleanup ister
- Negatif validation / authorization case’lerinde normalde delete gerekmez; absence verification gerekir

### Olası flaky riskler
- Submit sonrası UI refresh gecikmesi
- Listing ekranında görünürlük zamanlaması
- Önceki koşumlardan kalan aynı isimli veriler
- Role / credential konfigürasyonunun ortamlar arasında farklı olması
- Observation-sensitive boundary davranışlarının sürüm farkı göstermesi
