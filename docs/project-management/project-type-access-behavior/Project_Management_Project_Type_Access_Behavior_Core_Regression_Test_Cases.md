# Project Management - Project Type / Access Behavior - Core Regression Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Project Type / Access Behavior** fonksiyonu için belirlenmiş **Core Regression** senaryolarının detaylı test case karşılıklarını içerir.

Bu dokümanın amacı:
- proje tipine bağlı kritik erişim davranışlarını adım seviyesinde doğrulamak
- private ve team project farklarını çekirdek regresyon kapsamında test etmek
- proje tipi dönüşümü sonrası erişim ve permission etkilerini kontrol etmek
- UI ve API katmanlarında kritik authorization davranışlarını doğrulamaktır

Bu doküman yalnızca **çekirdek regresyon** kapsamını içerir. Extended coverage senaryoları ayrı aşamada ele alınacaktır.

---

## 2. Kapsamdaki Core Regression Senaryoları

- PM-PT-002 — Private project’te permission management bulunmamalı
- PM-PT-003 — Private project’e yalnız sahibi ve administrator erişebilmeli
- PM-PT-005 — Administrator private project’i team project’e dönüştürebilmeli
- PM-PT-006 — Project Manager private project’i team project’e dönüştürebilmeli
- PM-PT-007 — Project Member proje tipini değiştirememeli
- PM-PT-008 — Project Viewer proje tipini değiştirememeli
- PM-PT-009 — Team project private project’e dönüştürüldüğünde mevcut kullanıcı erişimleri otomatik kaldırılmamalı
- PM-PT-017 — Yetkisiz kullanıcı API üzerinden proje tipini değiştirememeli

---

## 3. Test Case Format

Her test case aşağıdaki alanları içerir:
- Test Case ID
- Related Scenario ID
- Test Case Name
- Priority
- Type
- Preconditions
- Test Steps
- Test Data
- Expected Result
- Postconditions
- Automation Candidate

---

## 4. Core Regression Test Cases

---

## TC-PM-PT-001
- **Test Case ID:** TC-PM-PT-001
- **Related Scenario ID:** PM-PT-002
- **Test Case Name:** Private project’te permission management alanı görünmemeli
- **Priority:** P0
- **Type:** Functional / Authorization / Negative
- **Preconditions:**
  - Mevcut bir private project olmalı
  - Yetkili bir kullanıcı ile sisteme giriş yapılmış olmalı
- **Test Steps:**
  1. Private project’i aç
  2. Project settings veya authorization/permissions alanlarına git
  3. User/group permission yönetimi ile ilgili alanları kontrol et
  4. Team project’e özgü permission management ekranlarına erişmeye çalış
- **Test Data:**
  - Project Type: `Private`
  - Target Project: `QA_Private_Project_001`
- **Expected Result:**
  - Private project’te permission management alanı görünmemelidir
  - Team project’e özgü user/group permission yönetimi kullanılamamalıdır
  - Kullanıcı private modele uygun ekran davranışı görmelidir
- **Postconditions:**
  - Projede herhangi bir değişiklik oluşmamalıdır
- **Automation Candidate:** UI automation

---

## TC-PM-PT-002
- **Test Case ID:** TC-PM-PT-002
- **Related Scenario ID:** PM-PT-003
- **Test Case Name:** Private project’e yalnız owner ve administrator erişebilmeli
- **Priority:** P0
- **Type:** Authorization
- **Preconditions:**
  - Mevcut bir private project olmalı
  - Owner, administrator ve ilgisiz user hesapları hazır olmalı
- **Test Steps:**
  1. Owner kullanıcı ile private project’e eriş
  2. Administrator kullanıcı ile aynı private project’e eriş
  3. İlgisiz kullanıcı ile aynı private project’e erişmeyi dene
  4. Gerekirse proje listesi, direkt URL ve API tarafında görünürlük/erişim kontrolü yap
- **Test Data:**
  - Project Type: `Private`
  - Target Project: `QA_Private_Access_Project_001`
  - Users:
    - `Owner User`
    - `Administrator User`
    - `Unauthorized User`
- **Expected Result:**
  - Owner private project’e erişebilmelidir
  - Administrator private project’e erişebilmelidir
  - Yetkisiz kullanıcı private project’e erişememelidir
- **Postconditions:**
  - Proje verisinde değişiklik olmamalıdır
- **Automation Candidate:** UI automation / API automation

---

## TC-PM-PT-003
- **Test Case ID:** TC-PM-PT-003
- **Related Scenario ID:** PM-PT-005
- **Test Case Name:** Administrator private project’i team project’e dönüştürebilmeli
- **Priority:** P0
- **Type:** Functional / Authorization
- **Preconditions:**
  - Administrator yetkili kullanıcı hazır olmalı
  - Dönüştürülebilir bir private project mevcut olmalı
- **Test Steps:**
  1. Administrator ile giriş yap
  2. Private project’i aç
  3. Project settings / edit ekranına git
  4. Private Project işaretini team davranışına çevirecek şekilde değiştir
  5. Formu kaydet
  6. Kaydetme sonrası proje ayarlarını ve davranışını kontrol et
- **Test Data:**
  - User Role: `Administrator`
  - Existing Project Type: `Private`
  - Target Project: `QA_Private_To_Team_Admin_001`
- **Expected Result:**
  - Proje başarıyla team project’e dönüştürülmelidir
  - Team project’e özgü permission/authorization alanları aktif hale gelmelidir
  - İşlem yetki hatası vermemelidir
- **Postconditions:**
  - Proje yeni tipiyle cleanup veya sonraki testler için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-PT-004
- **Test Case ID:** TC-PM-PT-004
- **Related Scenario ID:** PM-PT-006
- **Test Case Name:** Project Manager private project’i team project’e dönüştürebilmeli
- **Priority:** P0
- **Type:** Functional / Authorization
- **Preconditions:**
  - Project Manager yetkisine sahip kullanıcı hazır olmalı
  - Dönüştürülebilir bir private project mevcut olmalı
- **Test Steps:**
  1. Project Manager kullanıcı ile giriş yap
  2. Private project’i aç
  3. Project settings / edit ekranına git
  4. Private/team davranışını değiştirecek alanı güncelle
  5. Formu kaydet
  6. Kaydetme sonrası proje tipi davranışını kontrol et
- **Test Data:**
  - User Role: `Project Manager`
  - Existing Project Type: `Private`
  - Target Project: `QA_Private_To_Team_PM_001`
- **Expected Result:**
  - Dönüşüm başarılı olmalıdır
  - Proje team project gibi davranmaya başlamalıdır
  - Yetki hatası oluşmamalıdır
- **Postconditions:**
  - Proje yeni tipiyle cleanup veya sonraki testler için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-PT-005
- **Test Case ID:** TC-PM-PT-005
- **Related Scenario ID:** PM-PT-007
- **Test Case Name:** Project Member proje tipini değiştirememeli
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:**
  - Project Member rolüne sahip kullanıcı hazır olmalı
  - Hedef proje mevcut olmalı
- **Test Steps:**
  1. Project Member ile giriş yap
  2. Hedef projeyi aç
  3. Project settings veya edit alanına erişmeye çalış
  4. Proje tipi/private flag alanının görünürlüğünü kontrol et
  5. Mümkünse proje tipini değiştirmeyi dene
  6. Sonucu kontrol et
- **Test Data:**
  - User Role: `Project Member`
  - Target Project: `QA_Project_Type_Protected_001`
- **Expected Result:**
  - Project Member proje tipini değiştirememelidir
  - İlgili alan görünmemeli veya update işlemi reddedilmelidir
  - Proje tipi değişmemelidir
- **Postconditions:**
  - Hedef projenin tipi değişmemelidir
- **Automation Candidate:** UI automation / API automation

---

## TC-PM-PT-006
- **Test Case ID:** TC-PM-PT-006
- **Related Scenario ID:** PM-PT-008
- **Test Case Name:** Project Viewer proje tipini değiştirememeli
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:**
  - Project Viewer rolüne sahip kullanıcı hazır olmalı
  - Hedef proje mevcut olmalı
- **Test Steps:**
  1. Project Viewer ile giriş yap
  2. Hedef projeyi aç
  3. Project settings veya edit alanına erişmeye çalış
  4. Proje tipi/private flag alanının görünürlüğünü kontrol et
  5. Mümkünse proje tipini değiştirmeyi dene
  6. Sonucu kontrol et
- **Test Data:**
  - User Role: `Project Viewer`
  - Target Project: `QA_Project_Type_Protected_002`
- **Expected Result:**
  - Project Viewer proje tipini değiştirememelidir
  - Ayarlar alanı erişime kapalı olmalı veya işlem reddedilmelidir
  - Proje tipi değişmemelidir
- **Postconditions:**
  - Hedef projenin tipi değişmemelidir
- **Automation Candidate:** UI automation / API automation

---

## TC-PM-PT-007
- **Test Case ID:** TC-PM-PT-007
- **Related Scenario ID:** PM-PT-009
- **Test Case Name:** Team project private’a çevrildiğinde mevcut kullanıcı erişimleri otomatik silinmemeli
- **Priority:** P0
- **Type:** Data Integrity / Authorization
- **Preconditions:**
  - User/group assignment içeren bir team project mevcut olmalı
  - Dönüşüm öncesi erişim kayıtları not edilmiş olmalı
  - Yetkili kullanıcı dönüşüm yapabilmeli
- **Test Steps:**
  1. Kullanıcı/grup atamaları içeren team project’i aç
  2. Dönüşüm öncesi erişim kayıtlarını not et
  3. Projeyi team’den private’a dönüştür
  4. Dönüşüm sonrası project authorization / user assignment kayıtlarını kontrol et
  5. Önceki kayıtlarla karşılaştır
- **Test Data:**
  - Existing Project Type: `Team`
  - Target Project: `QA_Team_To_Private_Access_001`
  - Existing Assigned Users/Groups: `Predefined dataset`
- **Expected Result:**
  - Proje private modele geçmelidir
  - Mevcut kullanıcı/grup erişim kayıtları otomatik olarak sessizce kaldırılmamalıdır
  - Sistem manuel yönetim gerektiren tutarlı bir veri davranışı göstermelidir
- **Postconditions:**
  - Proje dönüşüm sonrası durumu cleanup veya sonraki testler için not edilmelidir
- **Automation Candidate:** API automation

---

## TC-PM-PT-008
- **Test Case ID:** TC-PM-PT-008
- **Related Scenario ID:** PM-PT-017
- **Test Case Name:** Yetkisiz kullanıcı API üzerinden proje tipini değiştirememeli
- **Priority:** P0
- **Type:** API / Authorization / Negative
- **Preconditions:**
  - Yetkisiz kullanıcıya ait API credentials hazır olmalı
  - Hedef proje mevcut olmalı
  - Proje tipi etkileyen update isteği hazırlanabilir olmalı
- **Test Steps:**
  1. Yetkisiz kullanıcı credentials ile authenticate ol
  2. Hedef proje için type/private flag değişikliği içeren update isteği hazırla
  3. API çağrısını gönder
  4. Response status/body bilgisini kontrol et
  5. Ardından proje bilgisini yeniden sorgula
- **Test Data:**
  - User Role: `Unauthorized User`
  - Target Project: `QA_API_Project_Type_Protected_001`
  - Attempted Change: `Private -> Team` veya `Team -> Private`
- **Expected Result:**
  - API isteği reddedilmelidir
  - Response yetki reddi veya başarısızlık bilgisi içermelidir
  - Proje tipi değişmemelidir
- **Postconditions:**
  - Hedef proje mevcut tipiyle kalmalıdır
- **Automation Candidate:** API automation

---

## 5. Execution Notes

- Private ve team dönüşüm testlerinde dönüşüm öncesi durum mutlaka kayıt altına alınmalıdır.
- Authorization testlerinde kullanıcı rollerinin gerçekten doğru atanmış olduğundan emin olunmalıdır.
- UI üzerinden yapılan proje tipi değişiklikleri mümkün olduğunda API ile çapraz doğrulanmalıdır.
- Private modele geçişte eski erişim kayıtlarının kaybolup kaybolmadığı özellikle kontrol edilmelidir.
- Test verilerinde aynı proje farklı testlerde kullanılıyorsa cleanup ve reset stratejisi belirlenmelidir.