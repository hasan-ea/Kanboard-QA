# Project Management - Edit Project - Core Regression Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Edit Project** fonksiyonu için belirlenmiş **Core Regression** senaryolarının detaylı test case karşılıklarını içerir.

Bu dokümanın amacı:
- kritik edit project akışlarını adım seviyesinde doğrulamak
- manuel test icrası için net kontrol adımları sağlamak
- sonraki aşamada UI/API automation implementasyonu için temel hazırlamak

Bu doküman yalnızca **çekirdek regresyon** kapsamını içerir. Extended coverage ve exploratory senaryolar ayrı aşamada ele alınacaktır.

---

## 2. Kapsamdaki Core Regression Senaryoları

- PM-EP-001 — Mevcut proje adı başarıyla güncellenebilmeli
- PM-EP-002 — Proje adı boş bırakıldığında güncelleme engellenmeli
- PM-EP-005 — Proje adı eski değerden yeni değere kalıcı olarak güncellenmeli
- PM-EP-006 — Edit sonrası proje bilgisi API tarafında doğrulanabilmeli
- PM-EP-009 — Kullanıcı kendi yetkisi dışında bir projeyi düzenleyememeli
- PM-EP-016 — API üzerinden yetkisiz proje güncelleme engellenmeli

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

## TC-PM-EP-001
- **Test Case ID:** TC-PM-EP-001
- **Related Scenario ID:** PM-EP-001
- **Test Case Name:** Geçerli yeni proje adı ile proje başarıyla güncellenebilmeli
- **Priority:** P0
- **Type:** Functional
- **Preconditions:**
  - Düzenlenebilir mevcut bir proje olmalı
  - Kullanıcı ilgili proje için edit yetkisine sahip olmalı
- **Test Steps:**
  1. Mevcut bir projeyi aç
  2. Edit project ekranına git
  3. Project Name alanındaki mevcut değeri sil
  4. Geçerli ve benzersiz yeni bir proje adı gir
  5. Formu kaydet
  6. Kaydetme sonrası ekrandaki sonucu kontrol et
- **Test Data:**
  - Existing Project Name: `QA_Edit_Target_001`
  - New Project Name: `QA_Edit_Renamed_001`
- **Expected Result:**
  - Proje adı başarıyla güncellenmelidir
  - Kullanıcı hata mesajı almamalıdır
  - Yeni proje adı ekranda görünmelidir
- **Postconditions:**
  - Proje yeni adıyla sistemde kalmalıdır veya cleanup için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-EP-002
- **Test Case ID:** TC-PM-EP-002
- **Related Scenario ID:** PM-EP-002
- **Test Case Name:** Proje adı boş bırakıldığında güncelleme engellenmeli
- **Priority:** P0
- **Type:** Validation / Negative
- **Preconditions:**
  - Düzenlenebilir mevcut bir proje olmalı
- **Test Steps:**
  1. Hedef projeyi aç
  2. Edit project ekranına git
  3. Project Name alanını tamamen boşalt
  4. Formu kaydet
  5. Validasyon mesajını ve sonuç durumunu kontrol et
- **Test Data:**
  - Project Name: `Empty`
- **Expected Result:**
  - Güncelleme tamamlanmamalıdır
  - Kullanıcıya anlamlı bir validasyon mesajı gösterilmelidir
  - Proje adı boş olarak kaydedilmemelidir
- **Postconditions:**
  - Proje eski geçerli adıyla kalmalıdır
- **Automation Candidate:** UI automation

---

## TC-PM-EP-003
- **Test Case ID:** TC-PM-EP-003
- **Related Scenario ID:** PM-EP-005
- **Test Case Name:** Güncellenen proje adı sayfa yenileme sonrası kalıcı olmalı
- **Priority:** P0
- **Type:** Data Integrity
- **Preconditions:**
  - Düzenlenebilir mevcut bir proje olmalı
  - Proje adı başarıyla güncellenmiş olmalı
- **Test Steps:**
  1. Hedef projeyi aç
  2. Edit project ekranına git
  3. Project Name alanını yeni bir geçerli değer ile değiştir
  4. Formu kaydet
  5. Sayfayı yenile
  6. Projeyi yeniden aç veya proje detayına geri dön
  7. Görünen proje adını kontrol et
- **Test Data:**
  - Existing Project Name: `QA_Edit_Persist_Old_001`
  - New Project Name: `QA_Edit_Persist_New_001`
- **Expected Result:**
  - Güncellenen proje adı kalıcı olmalıdır
  - Sayfa yenileme veya yeniden giriş sonrası eski isim geri dönmemelidir
- **Postconditions:**
  - Proje yeni adıyla kalmalıdır veya cleanup için işaretlenmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-EP-004
- **Test Case ID:** TC-PM-EP-004
- **Related Scenario ID:** PM-EP-006
- **Test Case Name:** UI üzerinden güncellenen proje adı API tarafında doğrulanabilmeli
- **Priority:** P0
- **Type:** Data Integrity / Consistency
- **Preconditions:**
  - UI ve API erişimi hazır olmalı
  - Düzenlenebilir mevcut proje olmalı
- **Test Steps:**
  1. UI üzerinden hedef projeyi aç
  2. Edit project ekranına git
  3. Project Name alanını yeni bir değer ile güncelle
  4. Formu kaydet
  5. Güncellenen proje adını not et
  6. API üzerinden ilgili projeyi sorgula
  7. API cevabındaki proje adını UI’daki değer ile karşılaştır
- **Test Data:**
  - Existing Project Name: `QA_UI_API_Edit_Target_001`
  - New Project Name: `QA_UI_API_Edit_Updated_001`
- **Expected Result:**
  - UI’da yapılan değişiklik API tarafında aynı şekilde görünmelidir
  - Proje adı UI ve API katmanlarında tutarlı olmalıdır
- **Postconditions:**
  - Güncellenen proje cleanup için not edilmelidir
- **Automation Candidate:** API automation

---

## TC-PM-EP-005
- **Test Case ID:** TC-PM-EP-005
- **Related Scenario ID:** PM-EP-009
- **Test Case Name:** Yetkisiz kullanıcı başka projeyi UI üzerinden düzenleyememeli
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:**
  - Edit yetkisi olmayan kullanıcı hesabı hazır olmalı
  - Kullanıcının düzenleme yetkisine sahip olmadığı hedef proje mevcut olmalı
- **Test Steps:**
  1. Yetkisiz kullanıcı ile giriş yap
  2. Hedef projenin detayına veya edit ekranına erişmeye çalış
  3. Edit alanlarının görünürlüğünü kontrol et
  4. Mümkünse update işlemi yapmayı dene
  5. Sonucu kontrol et
- **Test Data:**
  - User Role: `Unauthorized User`
  - Target Project: `QA_Restricted_Project_001`
  - Attempted New Name: `QA_Restricted_Project_Updated_001`
- **Expected Result:**
  - Kullanıcı edit ekranına erişememeli veya update işlemi yapamamalıdır
  - Proje bilgileri değişmemelidir
  - Sistem kontrollü erişim reddi davranışı göstermelidir
- **Postconditions:**
  - Hedef proje bilgilerinin değişmediği doğrulanmalıdır
- **Automation Candidate:** UI automation / API automation

---

## TC-PM-EP-006
- **Test Case ID:** TC-PM-EP-006
- **Related Scenario ID:** PM-EP-016
- **Test Case Name:** Yetkisiz kullanıcı API üzerinden proje güncelleyememeli
- **Priority:** P0
- **Type:** API / Authorization / Negative
- **Preconditions:**
  - Yetkisiz kullanıcıya ait API credentials hazır olmalı
  - Hedef proje mevcut olmalı
- **Test Steps:**
  1. Yetkisiz kullanıcı credentials ile authenticate ol
  2. Hedef proje için update isteği hazırla
  3. API üzerinden proje adı veya açıklamasını değiştirmeye çalış
  4. Response status/body bilgisini kontrol et
  5. Ardından proje verisini tekrar sorgula
- **Test Data:**
  - User Role: `Unauthorized User`
  - Target Project: `QA_API_Protected_Project_001`
  - Attempted New Name: `QA_API_Protected_Project_Updated_001`
- **Expected Result:**
  - API isteği reddedilmelidir
  - Proje bilgisi değişmemelidir
  - Response başarısızlık veya yetki reddi bilgisi içermelidir
- **Postconditions:**
  - Proje eski değeriyle kalmalıdır
- **Automation Candidate:** API automation

---

## 5. Execution Notes

- Proje adları benzersiz tutulmalıdır.
- Rename testlerinde eski ve yeni adların takibi dikkatli yapılmalıdır.
- Yetki testlerinde hedef projenin gerçekten erişim dışı olduğundan emin olunmalıdır.
- UI üzerinden yapılan update işlemleri mümkün olduğunda API ile çapraz doğrulanmalıdır.
- Pozitif edit senaryolarında cleanup stratejisi tanımlanmalıdır.
