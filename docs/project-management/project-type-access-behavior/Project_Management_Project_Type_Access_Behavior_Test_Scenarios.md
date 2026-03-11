# Project Management - Project Type / Access Behavior - Test Scenarios

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Project Type / Access Behavior** fonksiyonu için test senaryo kapsamını tanımlar.

Bu başlığın amacı:
- proje tipinin erişim modeline etkisini doğrulamak
- **Team Project** ve **Private Project** davranış farklarını kontrol etmek
- proje tipi dönüşümü sonrası erişim ve permission davranışını değerlendirmek
- rol bazlı erişim modelinin proje tipine göre doğru çalıştığını doğrulamak
- UI ve API katmanları arasında tutarlı davranış olup olmadığını gözlemlemektir

Bu doküman detaylı step-by-step test case dokümanı değildir.  
Bu doküman, bir sonraki aşamada üretilecek core regression test case ve extended coverage test case çalışmaları için temel senaryo setidir.

---

## 2. Kapsam

Bu alt başlıkta aşağıdaki alanlar kapsanır:

- Team project erişim modeli
- Private project erişim modeli
- Team project için kullanıcı / grup bazlı permission yönetimi
- Private project için permission management davranışı
- Private ↔ Team proje tipi dönüşümü
- Dönüşüm sonrası permission ve erişim etkileri
- Rol bazlı erişim farkları
- Viewer / Member / Project Manager davranışları
- Yetkisiz kullanıcıların proje tipi değişikliği girişimleri
- API üzerinden proje tipi / erişim davranışı doğrulamaları
- UI ve API tutarlılığı

Kapsam dışı:
- proje oluşturma
- proje adı / açıklama güncelleme
- proje görünüm ayarları
- varsayılan proje ayarları
- proje silme
- authorization konusunun sistem geneli detayları

Bu alanlar ilgili alt başlıklarda ayrıca ele alınacaktır.

---

## 3. Test Scenario List

---

### PM-PT-001
- **Scenario ID:** PM-PT-001
- **Scenario Name:** Team project kullanıcı ve grup yönetimini desteklemeli
- **Type:** Functional / Authorization
- **Priority:** P0
- **Preconditions:** Mevcut bir team project olmalı; yetkili kullanıcı erişimi olmalı
- **Test Description:** Kullanıcı team project üzerinde permissions/authorization alanlarına erişir ve kullanıcı/grup yönetimi yapılabildiğini doğrular
- **Expected Result:** Team project için kullanıcı/grup bazlı erişim yönetimi mümkün olmalıdır
- **Automation Decision:** UI automation

---

### PM-PT-002
- **Scenario ID:** PM-PT-002
- **Scenario Name:** Private project’te permission management bulunmamalı
- **Type:** Functional / Authorization / Negative
- **Priority:** P0
- **Preconditions:** Mevcut bir private project olmalı
- **Test Description:** Kullanıcı private project üzerinde team project’e özgü permission yönetimi ekranlarına erişmeye çalışır
- **Expected Result:** Private project’te permission management alanı bulunmamalı veya kullanıma kapalı olmalıdır
- **Automation Decision:** UI automation

---

### PM-PT-003
- **Scenario ID:** PM-PT-003
- **Scenario Name:** Private project’e yalnız sahibi ve administrator erişebilmeli
- **Type:** Authorization
- **Priority:** P0
- **Preconditions:** Mevcut bir private project olmalı; owner, admin ve farklı kullanıcı tipleri hazır olmalı
- **Test Description:** Private project’e owner, administrator ve ilgisiz kullanıcılar ile erişim denenir
- **Expected Result:** Owner ve administrator erişebilmelidir; yetkisiz kullanıcı erişememelidir
- **Automation Decision:** UI automation / API automation

---

### PM-PT-004
- **Scenario ID:** PM-PT-004
- **Scenario Name:** Team project’te project role ataması sonrası erişim davranışı rol bazında değişmeli
- **Type:** Authorization / Functional
- **Priority:** P0
- **Preconditions:** Team project mevcut olmalı; Project Manager, Member, Viewer kullanıcıları atanmış olmalı
- **Test Description:** Aynı team project’e farklı project role atanmış kullanıcılarla erişim ve temel kullanım davranışı gözlemlenir
- **Expected Result:** Rol bazlı erişim farkı görünmelidir; viewer read-only davranmalı, member/manager yetkileri ayrışmalıdır
- **Automation Decision:** UI automation / API automation

---

### PM-PT-005
- **Scenario ID:** PM-PT-005
- **Scenario Name:** Administrator private project’i team project’e dönüştürebilmeli
- **Type:** Functional / Authorization
- **Priority:** P0
- **Preconditions:** Administrator erişimi ve private project mevcut olmalı
- **Test Description:** Administrator edit/project settings üzerinden “Private Project” işaretini değiştirerek private → team dönüşümü yapar
- **Expected Result:** Proje başarıyla team project’e dönüşmelidir; team project davranışları aktif olmalıdır
- **Automation Decision:** UI automation

---

### PM-PT-006
- **Scenario ID:** PM-PT-006
- **Scenario Name:** Project Manager private project’i team project’e dönüştürebilmeli
- **Type:** Functional / Authorization
- **Priority:** P0
- **Preconditions:** Project Manager yetkili kullanıcı ve private project mevcut olmalı
- **Test Description:** Project Manager private ↔ team işaretini değiştirerek proje tipini dönüştürür
- **Expected Result:** Dönüşüm başarılı olmalıdır; edit sonrası proje team project gibi davranmalıdır
- **Automation Decision:** UI automation

---

### PM-PT-007
- **Scenario ID:** PM-PT-007
- **Scenario Name:** Project Member proje tipini değiştirememeli
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Team project veya private project mevcut olmalı; Project Member kullanıcı hazır olmalı
- **Test Description:** Project Member, proje tipi/private flag alanını değiştirmeye çalışır
- **Expected Result:** İşlem engellenmelidir; proje tipi değişmemelidir
- **Automation Decision:** UI automation / API automation

---

### PM-PT-008
- **Scenario ID:** PM-PT-008
- **Scenario Name:** Project Viewer proje tipini değiştirememeli
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Project Viewer kullanıcı ve hedef proje mevcut olmalı
- **Test Description:** Project Viewer proje ayarlarına erişip type/private flag değişikliği yapmaya çalışır
- **Expected Result:** Erişim veya update işlemi engellenmelidir
- **Automation Decision:** UI automation / API automation

---

### PM-PT-009
- **Scenario ID:** PM-PT-009
- **Scenario Name:** Team project private project’e dönüştürüldüğünde mevcut kullanıcı erişimleri otomatik kaldırılmamalı
- **Type:** Data Integrity / Authorization
- **Priority:** P0
- **Preconditions:** Kullanıcı/grup atanmış bir team project mevcut olmalı
- **Test Description:** Team project private’a çevrilir ve dönüşüm sonrası eski erişim kayıtları/üyelikleri kontrol edilir
- **Expected Result:** Mevcut kullanıcı erişimleri otomatik kaldırılmamalıdır; erişim listesi manuel yönetim gerektirmelidir
- **Automation Decision:** API automation

---

### PM-PT-010
- **Scenario ID:** PM-PT-010
- **Scenario Name:** Team → Private dönüşümü sonrası permission modeli private davranışına geçmeli
- **Type:** Functional / Authorization
- **Priority:** P1
- **Preconditions:** Team project private’a dönüştürülmüş olmalı
- **Test Description:** Dönüşüm sonrası permission ekranları, erişim görünürlüğü ve proje davranışı gözlemlenir
- **Expected Result:** Proje artık private modeline uygun davranmalı; team-specific permission yönetimi pasif olmalıdır
- **Automation Decision:** UI automation

---

### PM-PT-011
- **Scenario ID:** PM-PT-011
- **Scenario Name:** Private → Team dönüşümü sonrası permission management aktif hale gelmeli
- **Type:** Functional
- **Priority:** P1
- **Preconditions:** Private project team’e dönüştürülmüş olmalı
- **Test Description:** Dönüşüm sonrası permissions/user-group management alanları kontrol edilir
- **Expected Result:** Team project’e özgü permission yönetimi görünür ve kullanılabilir olmalıdır
- **Automation Decision:** UI automation

---

### PM-PT-012
- **Scenario ID:** PM-PT-012
- **Scenario Name:** Private → Team dönüşümü sonrası kullanıcıya project role atanabilmeli
- **Type:** Functional / Authorization
- **Priority:** P1
- **Preconditions:** Private project team project’e dönüştürülmüş olmalı
- **Test Description:** Dönüştürülen projeye kullanıcı eklenir ve project role atanır
- **Expected Result:** Kullanıcı ekleme ve rol atama işlemi başarılı olmalıdır
- **Automation Decision:** UI automation / API automation

---

### PM-PT-013
- **Scenario ID:** PM-PT-013
- **Scenario Name:** Team project’te Project Viewer read-only davranmalı
- **Type:** Authorization
- **Priority:** P1
- **Preconditions:** Team project’te viewer atanmış kullanıcı mevcut olmalı
- **Test Description:** Viewer ile projede board/task erişimi ve düzenleme aksiyonları denenir
- **Expected Result:** Viewer görüntüleme yapabilmeli ancak write/update aksiyonları yapamamalıdır
- **Automation Decision:** UI automation

---

### PM-PT-014
- **Scenario ID:** PM-PT-014
- **Scenario Name:** Team project’te Project Member board ve task kullanımına erişebilmeli
- **Type:** Authorization / Functional
- **Priority:** P1
- **Preconditions:** Team project’te member atanmış kullanıcı mevcut olmalı
- **Test Description:** Member ile projeye girilip temel task/board kullanımı denenir
- **Expected Result:** Member board’u kullanabilmeli ve task oluşturma gibi temel aksiyonlara erişebilmelidir
- **Automation Decision:** UI automation

---

### PM-PT-015
- **Scenario ID:** PM-PT-015
- **Scenario Name:** Team project’te Project Manager project settings erişimine sahip olmalı
- **Type:** Authorization / Functional
- **Priority:** P1
- **Preconditions:** Team project’te Project Manager atanmış kullanıcı mevcut olmalı
- **Test Description:** Project Manager proje ayarlarına, izinler ekranına ve yapılandırma alanlarına erişir
- **Expected Result:** Project Manager project settings alanlarını yönetebilmelidir
- **Automation Decision:** UI automation

---

### PM-PT-016
- **Scenario ID:** PM-PT-016
- **Scenario Name:** Private project’te kullanıcı ekleme/grup authorize işlemi yapılamamalı
- **Type:** Authorization / Negative
- **Priority:** P1
- **Preconditions:** Private project mevcut olmalı
- **Test Description:** Private project üzerinde user/group permission ekleme aksiyonu denenir
- **Expected Result:** İşlem desteklenmemeli veya ekran/aksiyon görünmemelidir
- **Automation Decision:** UI automation / API automation

---

### PM-PT-017
- **Scenario ID:** PM-PT-017
- **Scenario Name:** Yetkisiz kullanıcı API üzerinden proje tipini değiştirememeli
- **Type:** API / Authorization / Negative
- **Priority:** P0
- **Preconditions:** Yetkisiz user API credentials ve hedef proje mevcut olmalı
- **Test Description:** Yetkisiz kullanıcı API ile private/team dönüşümüne sebep olacak update isteği gönderir
- **Expected Result:** İstek reddedilmeli; proje tipi değişmemelidir
- **Automation Decision:** API automation

---

### PM-PT-018
- **Scenario ID:** PM-PT-018
- **Scenario Name:** Yetkili kullanıcı API üzerinden proje tipi davranışını değiştirebildiğinde UI ile tutarlı sonuç görülmeli
- **Type:** API / Consistency
- **Priority:** P1
- **Preconditions:** Yetkili kullanıcı API credentials; UI erişimi; hedef proje mevcut olmalı
- **Test Description:** API ile proje tipi/private flag etkileyen değişiklik yapılır, ardından UI’dan proje davranışı kontrol edilir
- **Expected Result:** API ve UI arasında proje tipi/erişim modeli tutarlı görünmelidir
- **Automation Decision:** API automation

---

### PM-PT-019
- **Scenario ID:** PM-PT-019
- **Scenario Name:** Team → Private dönüşümünde existing permission kayıtları veri kaybı olmadan korunmalı
- **Type:** Data Integrity
- **Priority:** P1
- **Preconditions:** User/group assignment içeren team project mevcut olmalı
- **Test Description:** Dönüşüm öncesi ve sonrası permission kayıtları karşılaştırılır
- **Expected Result:** Sistem kayıtları sessizce yok etmemeli; erişim listesi manuel yönetim ihtiyacına göre korunmalıdır
- **Automation Decision:** API automation

---

### PM-PT-020
- **Scenario ID:** PM-PT-020
- **Scenario Name:** Project type değişimi sonrası proje erişim davranışı hedef role göre yeniden doğrulanmalı
- **Type:** Authorization / Regression
- **Priority:** P1
- **Preconditions:** Private ↔ Team dönüşümü yapılmış proje; farklı rollerden kullanıcılar hazır olmalı
- **Test Description:** Dönüşüm sonrası admin, owner, project manager, member, viewer ve ilgisiz user ile erişim tekrar test edilir
- **Expected Result:** Yeni proje tipine uygun erişim modeli tutarlı biçimde çalışmalıdır
- **Automation Decision:** UI automation / API automation

---

## 4. Coverage Classification

### 4.1 Core Regression
Aşağıdaki senaryolar project type / access behavior için çekirdek regresyon seti olarak değerlendirilir:

- PM-PT-002
- PM-PT-003
- PM-PT-005
- PM-PT-006
- PM-PT-007
- PM-PT-008
- PM-PT-009
- PM-PT-017

### 4.2 Extended Coverage
Aşağıdaki senaryolar ikinci aşama kapsam genişletme seti olarak ele alınır:

- PM-PT-001
- PM-PT-004
- PM-PT-010
- PM-PT-011
- PM-PT-012
- PM-PT-013
- PM-PT-014
- PM-PT-015
- PM-PT-016
- PM-PT-018
- PM-PT-019
- PM-PT-020

### 4.3 Exploratory / Manual Discovery
Bu başlık için şu aşamada ayrı bir exploratory grup açılmamıştır. Belirsizlik görülürse test yürütümü sırasında ayrıca çıkarılacaktır.

### 4.4 Low Value / Optional
Bu başlık için şu aşamada low value olarak ayrılmış senaryo bulunmamaktadır.

---

## 5. Automation Strategy Summary

Bu alt başlık için önerilen otomasyon yaklaşımı:

### UI Automation için uygun senaryolar
- PM-PT-001
- PM-PT-002
- PM-PT-003
- PM-PT-004
- PM-PT-005
- PM-PT-006
- PM-PT-007
- PM-PT-008
- PM-PT-010
- PM-PT-011
- PM-PT-012
- PM-PT-013
- PM-PT-014
- PM-PT-015
- PM-PT-016
- PM-PT-020

### API Automation için uygun senaryolar
- PM-PT-003
- PM-PT-004
- PM-PT-007
- PM-PT-008
- PM-PT-009
- PM-PT-012
- PM-PT-016
- PM-PT-017
- PM-PT-018
- PM-PT-019
- PM-PT-020

### Manual Only
Bu başlık için şu aşamada manuel-only ayrılmış senaryo bulunmamaktadır.

### Not Worth Automating
Bu başlık için şu aşamada not-worth-automating ayrılmış senaryo bulunmamaktadır.

---

## 6. Notlar

- Bu doküman project type / access behavior kapsamının senaryo seviyesinde tanımını içerir.
- Bir sonraki aşamada yalnızca **Core Regression** senaryoları test case formatına indirgenecektir.
- Özellikle **private ↔ team dönüşümü sonrası erişim davranışı** bu başlığın en riskli alanıdır.
- Authorization davranışlarının daha geniş sistem etkileri ayrıca **Authorization / Negative Cases** başlığında derinleştirilecektir.