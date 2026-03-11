# Project Management - Project Type / Access Behavior - Extended Coverage Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Project Type / Access Behavior** fonksiyonu için belirlenmiş **Extended Coverage** senaryolarının detaylı test case karşılıklarını içerir.

Bu dokümanın amacı:
- team ve private project davranışlarının ek varyasyonlarını doğrulamak
- rol bazlı erişim farklarını kapsamı genişleterek test etmek
- proje tipi dönüşümü sonrası UI/API tutarlılığını gözlemlemek
- permission kayıtlarının korunması ve yeniden kullanılabilirliği gibi ikincil ama önemli davranışları doğrulamaktır

Bu doküman yalnızca **Extended Coverage** kapsamını içerir.

---

## 2. Kapsamdaki Extended Coverage Senaryoları

- PM-PT-001 — Team project kullanıcı ve grup yönetimini desteklemeli
- PM-PT-004 — Team project’te project role ataması sonrası erişim davranışı rol bazında değişmeli
- PM-PT-010 — Team → Private dönüşümü sonrası permission modeli private davranışına geçmeli
- PM-PT-011 — Private → Team dönüşümü sonrası permission management aktif hale gelmeli
- PM-PT-012 — Private → Team dönüşümü sonrası kullanıcıya project role atanabilmeli
- PM-PT-013 — Team project’te Project Viewer read-only davranmalı
- PM-PT-014 — Team project’te Project Member board ve task kullanımına erişebilmeli
- PM-PT-015 — Team project’te Project Manager project settings erişimine sahip olmalı
- PM-PT-016 — Private project’te kullanıcı ekleme/grup authorize işlemi yapılamamalı
- PM-PT-018 — Yetkili kullanıcı API üzerinden proje tipi davranışını değiştirebildiğinde UI ile tutarlı sonuç görülmeli
- PM-PT-019 — Team → Private dönüşümünde existing permission kayıtları veri kaybı olmadan korunmalı
- PM-PT-020 — Project type değişimi sonrası proje erişim davranışı hedef role göre yeniden doğrulanmalı

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

## 4. Extended Coverage Test Cases

---

## TC-PM-PT-009
- **Test Case ID:** TC-PM-PT-009
- **Related Scenario ID:** PM-PT-001
- **Test Case Name:** Team project kullanıcı ve grup yönetimini desteklemeli
- **Priority:** P1
- **Type:** Functional / Authorization
- **Preconditions:**
  - Team project mevcut olmalı
  - Yetkili kullanıcı project settings erişimine sahip olmalı
- **Test Steps:**
  1. Team project’i aç
  2. Project settings altında permissions alanına git
  3. User authorization ve group authorization seçeneklerini kontrol et
  4. Kullanıcı veya grup ekleme akışını aç
- **Test Data:**
  - Project Type: `Team`
  - Target Project: `QA_Team_Permissions_001`
- **Expected Result:**
  - Team project’te kullanıcı ve grup bazlı permission yönetimi mümkün olmalıdır
  - Permissions ekranı erişilebilir ve işlevsel olmalıdır
- **Postconditions:**
  - Test sırasında eklenen kullanıcı/gruplar cleanup için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-PT-010
- **Test Case ID:** TC-PM-PT-010
- **Related Scenario ID:** PM-PT-004
- **Test Case Name:** Team project rol ataması sonrası erişim davranışı rol bazında değişmeli
- **Priority:** P1
- **Type:** Authorization / Functional
- **Preconditions:**
  - Team project mevcut olmalı
  - PM, Member ve Viewer kullanıcıları projeye atanmış olmalı
- **Test Steps:**
  1. PM kullanıcı ile projeye eriş
  2. Member kullanıcı ile projeye eriş
  3. Viewer kullanıcı ile projeye eriş
  4. Board, task create ve project settings davranışlarını karşılaştır
- **Test Data:**
  - Project Roles: `Project Manager`, `Project Member`, `Project Viewer`
- **Expected Result:**
  - Erişim rol bazında farklılaşmalıdır
  - PM en geniş proje seviyesi erişime sahip olmalıdır
  - Viewer read-only davranmalıdır
- **Postconditions:**
  - Proje verisinde istenmeyen değişiklik oluşmamalıdır
- **Automation Candidate:** UI automation / API automation

---

## TC-PM-PT-011
- **Test Case ID:** TC-PM-PT-011
- **Related Scenario ID:** PM-PT-010
- **Test Case Name:** Team’den Private’a dönüşüm sonrası permission modeli private davranışına geçmeli
- **Priority:** P1
- **Type:** Functional / Authorization
- **Preconditions:**
  - Team project private’a dönüştürülmüş olmalı
- **Test Steps:**
  1. Dönüştürülen projeyi aç
  2. Project settings altında permissions alanını kontrol et
  3. User/group authorization aksiyonlarını dene
  4. Private erişim modeli ile uyumlu ekran davranışını gözlemle
- **Test Data:**
  - Target Project: `QA_Team_To_Private_Behavior_001`
- **Expected Result:**
  - Team’e özgü permission yönetimi aktif kullanılmamalıdır
  - Proje private davranışına geçmiş olmalıdır
- **Postconditions:**
  - Proje yeni tipiyle kalmalıdır veya reset edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-PT-012
- **Test Case ID:** TC-PM-PT-012
- **Related Scenario ID:** PM-PT-011
- **Test Case Name:** Private’dan Team’e dönüşüm sonrası permission management aktif hale gelmeli
- **Priority:** P1
- **Type:** Functional
- **Preconditions:**
  - Private project team’e dönüştürülmüş olmalı
- **Test Steps:**
  1. Dönüştürülen projeyi aç
  2. Project settings altında permissions alanına git
  3. User/group authorization alanlarının görünürlüğünü kontrol et
  4. Permission akışının açılabildiğini doğrula
- **Test Data:**
  - Target Project: `QA_Private_To_Team_Behavior_001`
- **Expected Result:**
  - Team project’e özgü permission management alanı görünür ve kullanılabilir olmalıdır
- **Postconditions:**
  - Eklenen test verileri cleanup için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-PT-013
- **Test Case ID:** TC-PM-PT-013
- **Related Scenario ID:** PM-PT-012
- **Test Case Name:** Private’dan Team’e dönüşüm sonrası kullanıcıya project role atanabilmeli
- **Priority:** P1
- **Type:** Functional / Authorization
- **Preconditions:**
  - Private project team project’e dönüştürülmüş olmalı
  - Yetkili kullanıcı permissions alanına erişebilmeli
- **Test Steps:**
  1. Dönüştürülen projeyi aç
  2. Permissions alanına git
  3. Yeni kullanıcı ekle
  4. Kullanıcıya project role ata
  5. Kaydet ve sonucu doğrula
- **Test Data:**
  - Assigned User: `QA_User_Assigned_001`
  - Assigned Role: `Project Member`
- **Expected Result:**
  - Kullanıcı ekleme ve role assignment işlemi başarılı olmalıdır
- **Postconditions:**
  - Eklenen kullanıcı ve rol cleanup için not edilmelidir
- **Automation Candidate:** UI automation / API automation

---

## TC-PM-PT-014
- **Test Case ID:** TC-PM-PT-014
- **Related Scenario ID:** PM-PT-013
- **Test Case Name:** Team project’te Project Viewer read-only davranmalı
- **Priority:** P1
- **Type:** Authorization
- **Preconditions:**
  - Team project’e viewer atanmış kullanıcı mevcut olmalı
- **Test Steps:**
  1. Viewer ile giriş yap
  2. Team project’i aç
  3. Board ve task detaylarını görüntüle
  4. Task create veya update aksiyonu dene
- **Test Data:**
  - User Role: `Project Viewer`
- **Expected Result:**
  - Viewer görüntüleme yapabilmelidir
  - Write/update/create aksiyonları yapamamalıdır
- **Postconditions:**
  - Proje verisi değişmemelidir
- **Automation Candidate:** UI automation

---

## TC-PM-PT-015
- **Test Case ID:** TC-PM-PT-015
- **Related Scenario ID:** PM-PT-014
- **Test Case Name:** Team project’te Project Member board ve task kullanımına erişebilmeli
- **Priority:** P1
- **Type:** Authorization / Functional
- **Preconditions:**
  - Team project’e member atanmış kullanıcı mevcut olmalı
- **Test Steps:**
  1. Member ile giriş yap
  2. Team project’i aç
  3. Board’a eriş
  4. Task create veya task interaction aksiyonunu dene
- **Test Data:**
  - User Role: `Project Member`
- **Expected Result:**
  - Member board’u kullanabilmeli
  - Temel task işlemlerine erişebilmelidir
- **Postconditions:**
  - Oluşan test task’ları cleanup için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-PT-016
- **Test Case ID:** TC-PM-PT-016
- **Related Scenario ID:** PM-PT-015
- **Test Case Name:** Team project’te Project Manager project settings erişimine sahip olmalı
- **Priority:** P1
- **Type:** Authorization / Functional
- **Preconditions:**
  - Team project’e Project Manager atanmış kullanıcı mevcut olmalı
- **Test Steps:**
  1. Project Manager ile giriş yap
  2. Projeyi aç
  3. Project settings alanına eriş
  4. Columns/categories/swimlanes/permissions ekranlarının açılabildiğini doğrula
- **Test Data:**
  - User Role: `Project Manager`
- **Expected Result:**
  - Project Manager project settings alanlarını görüntüleyebilmeli ve yönetebilmelidir
- **Postconditions:**
  - Ayarlar üzerinde yapılan test değişiklikleri cleanup için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-PT-017
- **Test Case ID:** TC-PM-PT-017
- **Related Scenario ID:** PM-PT-016
- **Test Case Name:** Private project’te kullanıcı veya grup authorize işlemi yapılamamalı
- **Priority:** P1
- **Type:** Authorization / Negative
- **Preconditions:**
  - Private project mevcut olmalı
- **Test Steps:**
  1. Private project’i aç
  2. Permissions veya user/group authorization akışını aramaya çalış
  3. Kullanıcı/grup ekleme aksiyonu başlatmayı dene
- **Test Data:**
  - Project Type: `Private`
- **Expected Result:**
  - User/group authorization akışı bulunmamalı veya kullanılamamalıdır
- **Postconditions:**
  - Projede yetki değişikliği oluşmamalıdır
- **Automation Candidate:** UI automation / API automation

---

## TC-PM-PT-018
- **Test Case ID:** TC-PM-PT-018
- **Related Scenario ID:** PM-PT-018
- **Test Case Name:** API ile yapılan proje tipi davranış değişikliği UI ile tutarlı görünmeli
- **Priority:** P1
- **Type:** API / Consistency
- **Preconditions:**
  - Yetkili kullanıcı API erişimine sahip olmalı
  - Hedef proje mevcut olmalı
- **Test Steps:**
  1. API ile proje tipini etkileyen değişikliği yap
  2. Response’ı doğrula
  3. UI’dan projeyi aç
  4. Görünen permission/erişim davranışını kontrol et
- **Test Data:**
  - Target Project: `QA_API_UI_Type_Consistency_001`
- **Expected Result:**
  - API sonucu ile UI davranışı tutarlı olmalıdır
- **Postconditions:**
  - Proje tipi reset veya cleanup için not edilmelidir
- **Automation Candidate:** API automation

---

## TC-PM-PT-019
- **Test Case ID:** TC-PM-PT-019
- **Related Scenario ID:** PM-PT-019
- **Test Case Name:** Team’den Private’a dönüşümde permission kayıtları veri kaybı olmadan korunmalı
- **Priority:** P1
- **Type:** Data Integrity
- **Preconditions:**
  - User/group assignment içeren team project mevcut olmalı
  - Dönüşüm öncesi kayıtlar not edilmiş olmalı
- **Test Steps:**
  1. Dönüşüm öncesi permission kayıtlarını API veya UI ile listele
  2. Projeyi private’a dönüştür
  3. Dönüşüm sonrası kayıtları tekrar al
  4. Önceki kayıtlarla karşılaştır
- **Test Data:**
  - Target Project: `QA_Permission_Preserve_001`
- **Expected Result:**
  - Permission kayıtları sessizce veri kaybına uğramamalıdır
- **Postconditions:**
  - Dönüşüm sonrası durum cleanup için not edilmelidir
- **Automation Candidate:** API automation

---

## TC-PM-PT-020
- **Test Case ID:** TC-PM-PT-020
- **Related Scenario ID:** PM-PT-020
- **Test Case Name:** Proje tipi değişimi sonrası erişim davranışı tüm ilgili roller için yeniden doğrulanmalı
- **Priority:** P1
- **Type:** Authorization / Regression
- **Preconditions:**
  - Private ↔ Team dönüşümü yapılmış hedef proje mevcut olmalı
  - Admin, owner, PM, member, viewer ve ilgisiz user hazır olmalı
- **Test Steps:**
  1. Proje tipini değiştir
  2. Her kullanıcı ile projeye erişmeyi dene
  3. Temel davranışları karşılaştır
  4. Yeni tipe göre erişim modelini doğrula
- **Test Data:**
  - Converted Project: `QA_Access_Revalidation_001`
- **Expected Result:**
  - Yeni proje tipine uygun erişim davranışı tutarlı olmalıdır
- **Postconditions:**
  - Proje ve role assignments cleanup için not edilmelidir
- **Automation Candidate:** UI automation / API automation

---

## 5. Execution Notes

- Bu başlıkta permission kayıtlarının korunması ve erişim modelinin gerçekten değişmesi iki ayrı doğrulama olarak ele alınmalıdır.
- Dönüşüm testlerinde role assignment öncesi/sonrası veri snapshot’ı almak faydalıdır.
- Viewer ve Member davranışları task-level modüllerle overlap edebilir; burada yalnız proje tipiyle ilişkili çekirdek davranış doğrulanmalıdır.
