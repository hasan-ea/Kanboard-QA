# Project Management - Edit Project - Extended Coverage Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Edit Project** fonksiyonu için belirlenmiş **Extended Coverage** senaryolarının detaylı test case karşılıklarını içerir.

Bu dokümanın amacı:
- Core Regression dışında kalan fakat işlevsel değer taşıyan senaryoları detaylandırmak
- edit project akışındaki ek validasyonları doğrulamak
- görünürlük, tutarlılık ve açıklama güncelleme davranışlarını sistematik olarak test etmek
- sonraki aşamada UI/API otomasyon adaylarını netleştirmek

Bu doküman yalnızca **Extended Coverage** kapsamını içerir. Exploratory / Manual Discovery ve Low Value / Optional senaryolar burada detaylandırılmamıştır.

---

## 2. Kapsamdaki Extended Coverage Senaryoları

- PM-EP-003 — Sadece boşluk karakterlerinden oluşan proje adı ile güncelleme engellenmeli
- PM-EP-007 — Yetkili kullanıcı proje açıklamasını güncelleyebilmeli
- PM-EP-010 — Normal user yalnız izinli olduğu projelerde edit yapabilmeli
- PM-EP-014 — Edit edilen proje adı proje listesinde güncel haliyle görünmeli
- PM-EP-015 — Edit edilen proje detay ekranında güncel bilgiler görünmeli
- PM-EP-017 — API üzerinden yetkili proje güncelleme başarılı olmalı
- PM-EP-018 — Edit ekranında mevcut proje bilgileri ön yüklü gelmeli

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

## TC-PM-EP-007
- **Test Case ID:** TC-PM-EP-007
- **Related Scenario ID:** PM-EP-003
- **Test Case Name:** Sadece boşluk karakterlerinden oluşan proje adı ile güncelleme engellenmeli
- **Priority:** P1
- **Type:** Validation / Negative
- **Preconditions:**
  - Düzenlenebilir mevcut bir proje olmalı
  - Kullanıcı edit yetkisine sahip olmalı
- **Test Steps:**
  1. Hedef projeyi aç
  2. Edit project ekranına git
  3. Project Name alanına yalnızca boşluk karakterleri gir
  4. Formu kaydet
  5. Validasyon mesajını ve sonuç durumunu kontrol et
- **Test Data:**
  - Project Name: `"     "`
- **Expected Result:**
  - Güncelleme tamamlanmamalıdır
  - Sistem yalnızca boşluk karakterlerinden oluşan değeri geçerli proje adı olarak kabul etmemelidir
  - Kullanıcıya anlamlı validasyon mesajı gösterilmelidir veya kayıt engellenmelidir
- **Postconditions:**
  - Proje eski geçerli adıyla kalmalıdır
- **Automation Candidate:** UI automation

---

## TC-PM-EP-008
- **Test Case ID:** TC-PM-EP-008
- **Related Scenario ID:** PM-EP-007
- **Test Case Name:** Yetkili kullanıcı proje açıklamasını başarıyla güncelleyebilmeli
- **Priority:** P1
- **Type:** Functional
- **Preconditions:**
  - Açıklama alanı bulunan düzenlenebilir bir proje olmalı
  - Kullanıcı ilgili proje için edit yetkisine sahip olmalı
- **Test Steps:**
  1. Hedef projeyi aç
  2. Edit project ekranına git
  3. Project Description alanını yeni geçerli bir değer ile güncelle
  4. Formu kaydet
  5. Kaydetme sonrası açıklama alanını kontrol et
- **Test Data:**
  - Existing Description: `Initial project description`
  - New Description: `Updated project description for edit validation`
- **Expected Result:**
  - Proje açıklaması başarıyla güncellenmelidir
  - Yeni açıklama ekranda görünmelidir
  - Kullanıcı hata mesajı almamalıdır
- **Postconditions:**
  - Güncellenen açıklama cleanup veya sonraki testler için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-EP-009
- **Test Case ID:** TC-PM-EP-009
- **Related Scenario ID:** PM-EP-010
- **Test Case Name:** Normal user izinli olduğu projede edit işlemi yapabilmeli
- **Priority:** P1
- **Type:** Authorization + Functional
- **Preconditions:**
  - Normal user hesabı hazır olmalı
  - Kullanıcının edit yetkisine sahip olduğu hedef proje mevcut olmalı
- **Test Steps:**
  1. Normal user ile giriş yap
  2. Yetkili olduğu hedef projeyi aç
  3. Edit project ekranına git
  4. Project Name veya Description alanında geçerli bir değişiklik yap
  5. Formu kaydet
  6. Sonucu kontrol et
- **Test Data:**
  - User Role: `User`
  - Target Project: `QA_User_Editable_Project_001`
  - New Project Name: `QA_User_Editable_Project_Updated_001`
- **Expected Result:**
  - Kullanıcı izinli olduğu projede update işlemini başarıyla yapabilmelidir
  - Değişiklikler sistemde görünmelidir
  - Yetki hatası alınmamalıdır
- **Postconditions:**
  - Güncellenen proje cleanup için not edilmelidir
- **Automation Candidate:** UI automation / API automation

---

## TC-PM-EP-010
- **Test Case ID:** TC-PM-EP-010
- **Related Scenario ID:** PM-EP-014
- **Test Case Name:** Edit edilen proje adı proje listesinde güncel haliyle görünmeli
- **Priority:** P1
- **Type:** Functional / Consistency
- **Preconditions:**
  - Düzenlenebilir proje mevcut olmalı
  - Proje adı başarıyla güncellenmiş olmalı
- **Test Steps:**
  1. Hedef projeyi aç
  2. Edit project ekranına git
  3. Project Name alanını yeni geçerli değer ile değiştir
  4. Formu kaydet
  5. Proje listesine geri dön
  6. İlgili projeyi listede bul
  7. Listede görünen proje adını kontrol et
- **Test Data:**
  - Existing Project Name: `QA_List_View_Old_001`
  - New Project Name: `QA_List_View_New_001`
- **Expected Result:**
  - Proje listesinde güncel proje adı görünmelidir
  - Eski ad listede görünmemelidir
- **Postconditions:**
  - Güncellenen proje cleanup için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-EP-011
- **Test Case ID:** TC-PM-EP-011
- **Related Scenario ID:** PM-EP-015
- **Test Case Name:** Edit edilen proje detay ekranında güncel bilgiler görünmeli
- **Priority:** P1
- **Type:** Functional / Consistency
- **Preconditions:**
  - Düzenlenebilir proje mevcut olmalı
  - Proje bilgileri başarıyla güncellenmiş olmalı
- **Test Steps:**
  1. Hedef projeyi aç
  2. Edit project ekranına git
  3. Project Name ve/veya Description alanını güncelle
  4. Formu kaydet
  5. Proje detay ekranına dön veya projeyi yeniden aç
  6. Görünen proje bilgilerini kontrol et
- **Test Data:**
  - New Project Name: `QA_Detail_View_New_001`
  - New Description: `Updated detail view description`
- **Expected Result:**
  - Güncellenen proje bilgileri detay ekranında doğru görünmelidir
  - Eski bilgiler görünmemelidir
- **Postconditions:**
  - Güncellenen proje cleanup için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-EP-012
- **Test Case ID:** TC-PM-EP-012
- **Related Scenario ID:** PM-EP-017
- **Test Case Name:** Yetkili kullanıcı API üzerinden proje bilgilerini güncelleyebilmeli
- **Priority:** P1
- **Type:** API / Functional
- **Preconditions:**
  - Yetkili kullanıcıya ait API credentials hazır olmalı
  - Hedef proje mevcut olmalı
- **Test Steps:**
  1. Yetkili kullanıcı credentials ile authenticate ol
  2. Hedef proje için update isteği hazırla
  3. API üzerinden proje adı veya açıklamasını güncelle
  4. Response status/body bilgisini kontrol et
  5. Proje verisini tekrar sorgula
  6. Güncellenen alanları doğrula
- **Test Data:**
  - User Role: `Authorized User`
  - Target Project: `QA_API_Editable_Project_001`
  - New Project Name: `QA_API_Editable_Project_Updated_001`
  - New Description: `Updated via API`
- **Expected Result:**
  - API update işlemi başarılı olmalıdır
  - Güncellenen alanlar sistemde yeni değerleri ile görünmelidir
  - Response başarılı işlem bilgisini içermelidir
- **Postconditions:**
  - Güncellenen proje cleanup için not edilmelidir
- **Automation Candidate:** API automation

---

## TC-PM-EP-013
- **Test Case ID:** TC-PM-EP-013
- **Related Scenario ID:** PM-EP-018
- **Test Case Name:** Edit ekranında mevcut proje bilgileri ön yüklü gelmeli
- **Priority:** P1
- **Type:** Functional / UX
- **Preconditions:**
  - Düzenlenebilir mevcut bir proje olmalı
  - Projede ad ve varsa açıklama gibi mevcut veriler bulunmalı
- **Test Steps:**
  1. Hedef projeyi aç
  2. Edit project ekranına git
  3. Form alanlarındaki mevcut değerleri gözlemle
  4. Project Name ve varsa Description alanlarının kayıtlı veri ile eşleştiğini kontrol et
- **Test Data:**
  - Existing Project Name: `QA_Prefill_Project_001`
  - Existing Description: `Prefilled project description`
- **Expected Result:**
  - Edit formunda mevcut proje adı ön yüklü gelmelidir
  - Varsa açıklama alanı da kayıtlı değeri göstermelidir
  - Form boş veya yanlış veri ile açılmamalıdır
- **Postconditions:**
  - Veri değişikliği yapılmadıysa proje üzerinde değişiklik oluşmamalıdır
- **Automation Candidate:** UI automation

---

## 5. Execution Notes

- Rename ve description update testlerinde proje verilerinin eski/yeni halleri dikkatli takip edilmelidir.
- UI tutarlılık kontrollerinde hem proje listesi hem detay ekranı ayrı ayrı doğrulanmalıdır.
- Yetkili normal user senaryosunda kullanıcıya gerçekten edit izni tanımlandığından emin olunmalıdır.
- API update testlerinde başarılı işlem sonrası veri yeniden sorgulanarak doğrulanmalıdır.
- Tüm proje isimleri benzersiz tutulmalı; tarih/saat suffix kullanılması önerilir.

---

## 6. Relationship with Other Documents

Bu doküman aşağıdaki dokümanların devamıdır:
- `Project_Management_Edit_Project_Test_Scenarios.md`
- `Project_Management_Edit_Project_Core_Regression_Test_Cases.md`

Dokümanlar arası ilişki:
- **Test Scenarios** → tüm kapsamı tanımlar
- **Core Regression Test Cases** → en kritik testleri detaylandırır
- **Extended Coverage Test Cases** → ek ama değerli kapsamı detaylandırır
