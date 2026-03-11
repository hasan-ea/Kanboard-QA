# Project Management - Create Project - Extended Coverage Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Create Project** fonksiyonu için belirlenmiş **Extended Coverage** senaryolarının detaylı test case karşılıklarını içerir.

Bu dokümanın amacı:
- Core Regression dışında kalan fakat işlevsel değer taşıyan senaryoları detaylandırmak
- create project akışındaki ek validasyonları doğrulamak
- mevcut projeden veri kopyalama davranışlarını sistematik olarak test etmek
- sonraki aşamada API/UI otomasyon adaylarını netleştirmek

Bu doküman yalnızca **Extended Coverage** kapsamını içerir. Exploratory / Manual Discovery senaryoları burada detaylandırılmamıştır.

---

## 2. Kapsamdaki Extended Coverage Senaryoları

- PM-CP-007 — Sadece boşluk karakterlerinden oluşan proje adı kabul edilmemeli
- PM-CP-010 — Başka projeden permissions kopyalanarak proje oluşturulabilmeli
- PM-CP-011 — Başka projeden automatic actions kopyalanarak proje oluşturulabilmeli
- PM-CP-012 — Başka projeden swimlanes kopyalanarak proje oluşturulabilmeli
- PM-CP-013 — Başka projeden categories kopyalanarak proje oluşturulabilmeli
- PM-CP-014 — Başka projeden tasks kopyalanarak proje oluşturulabilmeli
- PM-CP-015 — Hiçbir kopyalama seçeneği işaretlenmeden temiz proje oluşturulabilmeli

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

## TC-PM-CP-009
- **Test Case ID:** TC-PM-CP-009
- **Related Scenario ID:** PM-CP-007
- **Test Case Name:** Sadece boşluk karakterlerinden oluşan proje adı ile proje oluşturma engellenmeli
- **Priority:** P1
- **Type:** Validation / Negative
- **Preconditions:**
  - Kullanıcı create project ekranına erişebilmeli
- **Test Steps:**
  1. Project creation ekranına git
  2. Project Name alanına yalnızca boşluk karakterleri gir
  3. Geçerli bir project type seç
  4. Formu submit et
  5. Sonucu ve varsa validasyon mesajını kontrol et
- **Test Data:**
  - Project Name: `"     "`
  - Project Type: `Private`
- **Expected Result:**
  - Proje oluşturulmamalıdır
  - Sistem boşluk karakterlerinden oluşan değeri geçerli proje adı olarak kabul etmemelidir
  - Kullanıcıya anlamlı validasyon mesajı gösterilmelidir veya kayıt engellenmelidir
- **Postconditions:**
  - Yeni proje oluşmamalıdır
- **Automation Candidate:** UI automation

---

## TC-PM-CP-010
- **Test Case ID:** TC-PM-CP-010
- **Related Scenario ID:** PM-CP-010
- **Test Case Name:** Permissions kopyalanarak yeni proje oluşturulabilmeli
- **Priority:** P1
- **Type:** Functional / Data Integrity
- **Preconditions:**
  - Permissions tanımlı kaynak proje mevcut olmalı
  - Kullanıcı create project ve source project seçme yetkisine sahip olmalı
- **Test Steps:**
  1. Permissions tanımlı kaynak projeyi hazırla veya doğrula
  2. Project creation ekranına git
  3. Benzersiz yeni proje adı gir
  4. Uygun project type seç
  5. Kaynak proje olarak permissions içeren projeyi seç
  6. Permissions kopyalama seçeneğini işaretle
  7. Formu submit et
  8. Oluşturulan yeni projenin permission yapısını kontrol et
- **Test Data:**
  - Source Project: `SRC_Project_With_Permissions`
  - New Project Name: `QA_Copy_Permissions_001`
  - Copy Option: `Permissions`
- **Expected Result:**
  - Yeni proje başarıyla oluşturulmalıdır
  - Kaynak projedeki permission yapısı yeni projeye taşınmalıdır
  - Beklenmeyen ekstra yetki veya eksik yetki oluşmamalıdır
- **Postconditions:**
  - Oluşturulan proje ve kopyalanan permission yapısı cleanup veya sonraki testler için not edilmelidir
- **Automation Candidate:** API automation

---

## TC-PM-CP-011
- **Test Case ID:** TC-PM-CP-011
- **Related Scenario ID:** PM-CP-011
- **Test Case Name:** Automatic actions kopyalanarak yeni proje oluşturulabilmeli
- **Priority:** P1
- **Type:** Functional / Data Integrity
- **Preconditions:**
  - Automatic action tanımlı kaynak proje mevcut olmalı
- **Test Steps:**
  1. Automatic actions içeren kaynak projeyi doğrula
  2. Project creation ekranına git
  3. Yeni proje adı gir
  4. Kaynak projeyi seç
  5. Automatic actions kopyalama seçeneğini işaretle
  6. Formu submit et
  7. Yeni projedeki automatic actions kayıtlarını kontrol et
- **Test Data:**
  - Source Project: `SRC_Project_With_Actions`
  - New Project Name: `QA_Copy_Actions_001`
  - Copy Option: `Automatic Actions`
- **Expected Result:**
  - Yeni proje başarıyla oluşturulmalıdır
  - Kaynak projedeki automatic actions kayıtları yeni projede oluşmalıdır
  - Action sayısı ve temel tanımlar beklenen şekilde taşınmalıdır
- **Postconditions:**
  - Oluşturulan proje cleanup için not edilmelidir
- **Automation Candidate:** API automation

---

## TC-PM-CP-012
- **Test Case ID:** TC-PM-CP-012
- **Related Scenario ID:** PM-CP-012
- **Test Case Name:** Swimlanes kopyalanarak yeni proje oluşturulabilmeli
- **Priority:** P1
- **Type:** Functional / Data Integrity
- **Preconditions:**
  - Birden fazla swimlane tanımlı kaynak proje mevcut olmalı
- **Test Steps:**
  1. Swimlane içeren kaynak projeyi doğrula
  2. Project creation ekranına git
  3. Yeni proje adı gir
  4. Kaynak proje seç
  5. Swimlanes kopyalama seçeneğini işaretle
  6. Formu submit et
  7. Yeni projedeki swimlane listesini kontrol et
- **Test Data:**
  - Source Project: `SRC_Project_With_Swimlanes`
  - New Project Name: `QA_Copy_Swimlanes_001`
  - Copy Option: `Swimlanes`
- **Expected Result:**
  - Yeni proje başarıyla oluşturulmalıdır
  - Kaynak projedeki swimlane yapısı yeni projeye taşınmalıdır
  - Swimlane sayısı ve isimleri beklenen şekilde görünmelidir
- **Postconditions:**
  - Oluşturulan proje cleanup için not edilmelidir
- **Automation Candidate:** API automation

---

## TC-PM-CP-013
- **Test Case ID:** TC-PM-CP-013
- **Related Scenario ID:** PM-CP-013
- **Test Case Name:** Categories kopyalanarak yeni proje oluşturulabilmeli
- **Priority:** P1
- **Type:** Functional / Data Integrity
- **Preconditions:**
  - Category tanımlı kaynak proje mevcut olmalı
- **Test Steps:**
  1. Category içeren kaynak projeyi doğrula
  2. Project creation ekranına git
  3. Yeni proje adı gir
  4. Kaynak proje seç
  5. Categories kopyalama seçeneğini işaretle
  6. Formu submit et
  7. Yeni projedeki category listesini kontrol et
- **Test Data:**
  - Source Project: `SRC_Project_With_Categories`
  - New Project Name: `QA_Copy_Categories_001`
  - Copy Option: `Categories`
- **Expected Result:**
  - Yeni proje başarıyla oluşturulmalıdır
  - Kaynak projedeki category kayıtları yeni projeye taşınmalıdır
  - Category sayısı ve temel değerler beklenen şekilde eşleşmelidir
- **Postconditions:**
  - Oluşturulan proje cleanup için not edilmelidir
- **Automation Candidate:** API automation

---

## TC-PM-CP-014
- **Test Case ID:** TC-PM-CP-014
- **Related Scenario ID:** PM-CP-014
- **Test Case Name:** Tasks kopyalanarak yeni proje oluşturulabilmeli
- **Priority:** P1
- **Type:** Functional / Data Integrity
- **Preconditions:**
  - İçinde task bulunan kaynak proje mevcut olmalı
- **Test Steps:**
  1. Task içeren kaynak projeyi doğrula
  2. Project creation ekranına git
  3. Yeni proje adı gir
  4. Kaynak proje seç
  5. Tasks kopyalama seçeneğini işaretle
  6. Formu submit et
  7. Yeni projedeki task listesini kontrol et
- **Test Data:**
  - Source Project: `SRC_Project_With_Tasks`
  - New Project Name: `QA_Copy_Tasks_001`
  - Copy Option: `Tasks`
- **Expected Result:**
  - Yeni proje başarıyla oluşturulmalıdır
  - Kaynak projedeki task kayıtları yeni projeye taşınmalıdır
  - Task sayısı ve temel task verileri beklenen şekilde oluşmalıdır
- **Postconditions:**
  - Oluşturulan proje cleanup için not edilmelidir
- **Automation Candidate:** API automation

---

## TC-PM-CP-015
- **Test Case ID:** TC-PM-CP-015
- **Related Scenario ID:** PM-CP-015
- **Test Case Name:** Kopyalama seçeneği kullanılmadan temiz proje oluşturulabilmeli
- **Priority:** P1
- **Type:** Functional
- **Preconditions:**
  - Kullanıcı create project ekranına erişebilmeli
- **Test Steps:**
  1. Project creation ekranına git
  2. Benzersiz yeni proje adı gir
  3. Geçerli bir project type seç
  4. Hiçbir source project veya copy seçeneği işaretleme
  5. Formu submit et
  6. Oluşturulan projeyi kontrol et
- **Test Data:**
  - New Project Name: `QA_Clean_Create_001`
  - Project Type: `Private`
- **Expected Result:**
  - Proje başarıyla oluşturulmalıdır
  - Proje temiz/standart başlangıç yapısıyla açılmalıdır
  - Kaynak projeden veri taşınmamalıdır
- **Postconditions:**
  - Oluşturulan proje cleanup için not edilmelidir
- **Automation Candidate:** UI automation

---

## 5. Execution Notes

- Copy senaryolarında kaynak proje verisi test öncesinde doğrulanmalıdır.
- Kaynak projelerin sabit test verisi olarak tutulması otomasyon stabilitesi açısından faydalıdır.
- Task, category, swimlane, action ve permission doğrulamaları mümkün olduğunda API üzerinden yapılmalıdır.
- Proje isimleri benzersiz tutulmalı; tarih/saat suffix kullanılması önerilir.
- Extended coverage senaryoları çekirdek regresyon kadar sık koşulmayabilir, ancak ürün davranışını derinleştirmek için değerlidir.

---

## 6. Relationship with Other Documents

Bu doküman aşağıdaki dokümanların devamıdır:
- `Project_Management_Create_Project_Test_Scenarios.md`
- `Project_Management_Create_Project_Core_Regression_Test_Cases.md`

Dokümanlar arası ilişki:
- **Test Scenarios** → tüm kapsamı tanımlar
- **Core Regression Test Cases** → en kritik testleri detaylandırır
- **Extended Coverage Test Cases** → ek ama değerli kapsamı detaylandırır
