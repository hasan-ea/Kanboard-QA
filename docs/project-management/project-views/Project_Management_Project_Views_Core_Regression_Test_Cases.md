# Project Management - Project Views - Core Regression Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Project Views** fonksiyonu için belirlenmiş **Core Regression** senaryolarının detaylı test case karşılıklarını içerir.

---

## 2. Kapsamdaki Core Regression Senaryoları

- PM-PV-001 — Project board görünümü açılabilmeli
- PM-PV-002 — Project list görünümü açılabilmeli
- PM-PV-005 — View değişimi görev verisini değiştirmemeli
- PM-PV-006 — Search/filter sonucu board view’da doğru yansımalı
- PM-PV-010 — Board görünümünde task sütunlar arasında drag & drop ile taşınabilmeli

---

## 3. Test Case Format

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

## TC-PM-PV-001
- **Test Case ID:** TC-PM-PV-001
- **Related Scenario ID:** PM-PV-001
- **Test Case Name:** Project board görünümü başarıyla açılabilmeli
- **Priority:** P0
- **Type:** Functional
- **Preconditions:**
  - Görev içeren proje mevcut olmalı
- **Test Steps:**
  1. Projeyi aç
  2. Board view’a git
  3. Sütunları ve task kartlarını kontrol et
- **Test Data:**
  - Target Project: `QA_Project_Views_001`
- **Expected Result:**
  - Board görünümü yüklenmelidir
  - Task’lar ilgili sütunlarda görünmelidir
- **Postconditions:**
  - Değişiklik yapılmadıysa veri değişmemelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-002
- **Test Case ID:** TC-PM-PV-002
- **Related Scenario ID:** PM-PV-002
- **Test Case Name:** Project list görünümü başarıyla açılabilmeli
- **Priority:** P0
- **Type:** Functional
- **Preconditions:**
  - Görev içeren proje mevcut olmalı
- **Test Steps:**
  1. Projeyi aç
  2. List view’a geç
  3. Liste satırlarını kontrol et
- **Test Data:**
  - Target Project: `QA_Project_Views_001`
- **Expected Result:**
  - List görünümü yüklenmelidir
  - Task’lar listede görünmelidir
- **Postconditions:**
  - Veri değişmemelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-003
- **Test Case ID:** TC-PM-PV-003
- **Related Scenario ID:** PM-PV-005
- **Test Case Name:** View değişimi task verisini değiştirmemeli
- **Priority:** P0
- **Type:** Data Integrity
- **Preconditions:**
  - Çoklu task içeren proje mevcut olmalı
- **Test Steps:**
  1. Board view’daki task sayısını ve örnek task’ları not et
  2. List view’a geç
  3. Gerekirse calendar/gantt view’lara geç
  4. Aynı task verisini karşılaştır
- **Test Data:**
  - Target Project: `QA_Project_Views_002`
- **Expected Result:**
  - View değişimi yalnız sunumu değiştirmelidir
  - Task sayısı ve temel veri korunmalıdır
- **Postconditions:**
  - Veri değişmemelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-004
- **Test Case ID:** TC-PM-PV-004
- **Related Scenario ID:** PM-PV-006
- **Test Case Name:** Search/filter sonucu board görünümünde doğru yansımalı
- **Priority:** P0
- **Type:** Functional / Search
- **Preconditions:**
  - Filtrelenebilir task verisi olmalı
- **Test Steps:**
  1. Board view’ı aç
  2. Search/filter alanına geçerli sorgu gir
  3. Görünen task’ları kontrol et
- **Test Data:**
  - Search Query: `assignee:me` veya uygun test sorgusu
- **Expected Result:**
  - Yalnız filtreye uyan task’lar görünmelidir
- **Postconditions:**
  - Filtre temizlenebilir olmalıdır
- **Automation Candidate:** UI automation

## TC-PM-PV-005
- **Test Case ID:** TC-PM-PV-005
- **Related Scenario ID:** PM-PV-010
- **Test Case Name:** Board üzerinde task drag & drop ile başka sütuna taşınabilmeli
- **Priority:** P0
- **Type:** Functional
- **Preconditions:**
  - Hareket ettirilebilir task mevcut olmalı
  - Board görünümü açık olmalı
- **Test Steps:**
  1. Board view’ı aç
  2. Hedef task’ı seç
  3. Task’ı başka bir sütuna sürükle bırak
  4. Yeni konumu kontrol et
- **Test Data:**
  - Source Column: `Ready`
  - Target Column: `Work in progress`
  - Task: `QA Drag Task 001`
- **Expected Result:**
  - Task yeni sütuna taşınmalıdır
  - Board görünümü anında güncellenmelidir
- **Postconditions:**
  - Task konumu cleanup veya reset için not edilmelidir
- **Automation Candidate:** UI automation