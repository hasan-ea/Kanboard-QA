# Project Management - Create Project - Core Regression Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Create Project** fonksiyonu için belirlenmiş **Core Regression** senaryolarının detaylı test case karşılıklarını içerir.

Bu dokümanın amacı:
- kritik create project akışlarını adım seviyesinde doğrulamak
- manuel test icrası için net kontrol adımları sağlamak
- sonraki aşamada UI/API automation backlog oluşturmak için temel hazırlamak

Bu doküman yalnızca **çekirdek regresyon** kapsamını içerir. Extended coverage ve exploratory senaryolar ayrı aşamada ele alınacaktır.

---

## 2. Kapsamdaki Core Regression Senaryoları

- PM-CP-001 — Private project başarıyla oluşturulabilmeli
- PM-CP-002 — Administrator team project oluşturabilmeli
- PM-CP-003 — Manager team project oluşturabilmeli
- PM-CP-004 — Normal user team project oluşturamamalı
- PM-CP-005 — Normal user private project oluşturabilmeli
- PM-CP-006 — Proje adı zorunlu olmalı
- PM-CP-019 — UI üzerinden oluşturulan proje API tarafında doğrulanabilmeli
- PM-CP-020 — Yetkisiz kullanıcı ile API üzerinden team project oluşturma engellenmeli

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

## TC-PM-CP-001
- **Test Case ID:** TC-PM-CP-001
- **Related Scenario ID:** PM-CP-001
- **Test Case Name:** Geçerli bilgiler ile private project oluşturulabilmeli
- **Priority:** P0
- **Type:** Functional
- **Preconditions:**
  - Sistemde giriş yapılmış olmalı
  - Kullanıcının project creation ekranına erişimi olmalı
- **Test Steps:**
  1. Project creation ekranına git
  2. Project Name alanına benzersiz geçerli bir proje adı gir
  3. Project Type olarak **Private Project** seç
  4. Formu submit et
  5. Oluşturma sonrası açılan ekranı veya project list ekranını kontrol et
- **Test Data:**
  - Project Name: `QA_Create_Private_001`
  - Project Type: `Private`
- **Expected Result:**
  - Proje başarıyla oluşturulmalıdır
  - Kullanıcı hata mesajı almamalıdır
  - Yeni proje proje listesinde veya proje detay ekranında görünmelidir
  - Oluşturulan proje adı girilen veri ile aynı olmalıdır
- **Postconditions:**
  - Oluşturulan test projesi cleanup için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-CP-002
- **Test Case ID:** TC-PM-CP-002
- **Related Scenario ID:** PM-CP-002
- **Test Case Name:** Administrator team project oluşturabilmeli
- **Priority:** P0
- **Type:** Authorization + Functional
- **Preconditions:**
  - Administrator rolünde kullanıcı ile giriş yapılmış olmalı
- **Test Steps:**
  1. Project creation ekranına git
  2. Geçerli ve benzersiz bir proje adı gir
  3. Project Type olarak **Team Project** seç
  4. Formu submit et
  5. Oluşturulan projenin başarıyla kaydedildiğini kontrol et
- **Test Data:**
  - User Role: `Administrator`
  - Project Name: `QA_Create_Team_Admin_001`
  - Project Type: `Team`
- **Expected Result:**
  - Team project başarıyla oluşturulmalıdır
  - Yetki hatası alınmamalıdır
  - Proje sistemde görünmelidir
- **Postconditions:**
  - Oluşturulan test projesi cleanup için işaretlenmelidir
- **Automation Candidate:** UI automation / API automation

---

## TC-PM-CP-003
- **Test Case ID:** TC-PM-CP-003
- **Related Scenario ID:** PM-CP-003
- **Test Case Name:** Manager team project oluşturabilmeli
- **Priority:** P0
- **Type:** Authorization + Functional
- **Preconditions:**
  - Manager rolünde kullanıcı ile giriş yapılmış olmalı
- **Test Steps:**
  1. Project creation ekranına git
  2. Geçerli ve benzersiz bir proje adı gir
  3. Project Type olarak **Team Project** seç
  4. Formu submit et
  5. Sonuç ekranını kontrol et
- **Test Data:**
  - User Role: `Manager`
  - Project Name: `QA_Create_Team_Manager_001`
  - Project Type: `Team`
- **Expected Result:**
  - Team project oluşturulabilmelidir
  - İşlem yetki hatası vermemelidir
  - Proje sistemde görünmelidir
- **Postconditions:**
  - Oluşturulan test projesi cleanup için not edilmelidir
- **Automation Candidate:** UI automation / API automation

---

## TC-PM-CP-004
- **Test Case ID:** TC-PM-CP-004
- **Related Scenario ID:** PM-CP-004
- **Test Case Name:** Normal user team project oluşturamamalı
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:**
  - Normal user rolünde kullanıcı ile giriş yapılmış olmalı
- **Test Steps:**
  1. Project creation ekranına git
  2. Geçerli bir proje adı gir
  3. Team project seçeneğinin görünürlüğünü kontrol et
  4. Görünüyorsa Team Project seçerek submit etmeyi dene
  5. Sonucu kontrol et
- **Test Data:**
  - User Role: `User`
  - Project Name: `QA_Create_Team_User_Forbidden_001`
  - Project Type: `Team`
- **Expected Result:**
  - Normal user team project oluşturamamalıdır
  - Beklenen davranış aşağıdakilerden biri olmalıdır:
    - Team project seçeneği hiç görünmez
    - Ya da submit sonrası yetki/validation hatası alınır
  - Her iki durumda da proje oluşturulmamalıdır
- **Postconditions:**
  - Sistemde ilgili isimde proje oluşmadığı doğrulanmalıdır
- **Automation Candidate:** UI automation / API automation

---

## TC-PM-CP-005
- **Test Case ID:** TC-PM-CP-005
- **Related Scenario ID:** PM-CP-005
- **Test Case Name:** Normal user private project oluşturabilmeli
- **Priority:** P0
- **Type:** Authorization + Functional
- **Preconditions:**
  - Normal user rolünde kullanıcı ile giriş yapılmış olmalı
- **Test Steps:**
  1. Project creation ekranına git
  2. Geçerli ve benzersiz bir proje adı gir
  3. Project Type olarak **Private Project** seç
  4. Formu submit et
  5. Oluşturma sonucunu kontrol et
- **Test Data:**
  - User Role: `User`
  - Project Name: `QA_Create_Private_User_001`
  - Project Type: `Private`
- **Expected Result:**
  - Private project başarıyla oluşturulmalıdır
  - Kullanıcı yetki hatası almamalıdır
  - Yeni proje sistemde görünmelidir
- **Postconditions:**
  - Oluşturulan test projesi cleanup için not edilmelidir
- **Automation Candidate:** UI automation

---

## TC-PM-CP-006
- **Test Case ID:** TC-PM-CP-006
- **Related Scenario ID:** PM-CP-006
- **Test Case Name:** Proje adı boş bırakıldığında oluşturma engellenmeli
- **Priority:** P0
- **Type:** Validation / Negative
- **Preconditions:**
  - Kullanıcı create project ekranına erişebilmeli
- **Test Steps:**
  1. Project creation ekranına git
  2. Project Name alanını boş bırak
  3. Geçerli bir project type seç
  4. Formu submit et
  5. Ekrandaki hata/validasyon mesajını kontrol et
- **Test Data:**
  - Project Name: `Empty`
  - Project Type: `Private` veya sistemde varsayılan geçerli tip
- **Expected Result:**
  - Proje oluşturulmamalıdır
  - Kullanıcıya anlamlı bir validasyon mesajı gösterilmelidir
  - Form submit olmuş gibi davranmamalıdır
- **Postconditions:**
  - Yeni proje oluşmamalıdır
- **Automation Candidate:** UI automation

---

## TC-PM-CP-007
- **Test Case ID:** TC-PM-CP-007
- **Related Scenario ID:** PM-CP-019
- **Test Case Name:** UI üzerinden oluşturulan private project API ile doğrulanabilmeli
- **Priority:** P0
- **Type:** Data Integrity / Consistency
- **Preconditions:**
  - UI ve API erişimi hazır olmalı
  - Test kullanıcısının create project yetkisi olmalı
- **Test Steps:**
  1. UI üzerinden benzersiz bir private project oluştur
  2. Oluşturulan proje adını not et
  3. API ile project list veya project detail bilgilerini çek
  4. UI’da oluşturulan proje ile API cevabındaki kaydı eşleştir
  5. Ad, tip ve temel proje bilgilerini karşılaştır
- **Test Data:**
  - Project Name: `QA_UI_API_Consistency_Private_001`
  - Project Type: `Private`
- **Expected Result:**
  - UI üzerinden oluşturulan proje API tarafında bulunabilmelidir
  - Proje adı UI ve API tarafında birebir eşleşmelidir
  - Proje tipi UI’daki seçim ile tutarlı olmalıdır
- **Postconditions:**
  - Oluşturulan proje cleanup için not edilmelidir
- **Automation Candidate:** API automation

---

## TC-PM-CP-008
- **Test Case ID:** TC-PM-CP-008
- **Related Scenario ID:** PM-CP-020
- **Test Case Name:** Yetkisiz kullanıcı API üzerinden team project oluşturamamalı
- **Priority:** P0
- **Type:** API / Authorization / Negative
- **Preconditions:**
  - Normal user API kimlik bilgileri hazır olmalı
  - Team project oluşturma endpoint/method bilgisi hazır olmalı
- **Test Steps:**
  1. Normal user credentials ile authenticate ol
  2. API üzerinden team project oluşturma isteği gönder
  3. Response status/body bilgisini kontrol et
  4. Ardından proje listesinden ilgili kayıt oluşmuş mu doğrula
- **Test Data:**
  - User Role: `User`
  - Project Name: `QA_API_Team_Forbidden_001`
  - Project Type: `Team`
- **Expected Result:**
  - API isteği reddedilmelidir
  - Response içinde yetki reddi veya başarısızlık bilgisi dönmelidir
  - Sistemde yeni team project oluşmamalıdır
- **Postconditions:**
  - Kayıt oluşmadığı doğrulanmalıdır
- **Automation Candidate:** API automation

---

## 5. Execution Notes

- Test verilerinde kullanılan proje adları benzersiz tutulmalıdır.
- Her test koşumunda tarih/saat suffix eklemek iyi pratiktir.
- Pozitif create senaryolarında cleanup stratejisi tanımlanmalıdır.
- Authorization testlerinde aynı davranış UI ve API arasında tutarlı mı ayrıca gözlemlenmelidir.
- PM-CP-003 senaryosu özellikle ürün dokümanı ile gerçek sistem davranışı arasında fark yakalamak açısından önemlidir.

---

## 6. Recommended Next Output

Bu dokümandan sonra önerilen sıradaki çıktı:
1. **Create Project - Automation Backlog**
2. **Create Project - Test Data Matrix**
3. **Create Project - RTM Mapping**