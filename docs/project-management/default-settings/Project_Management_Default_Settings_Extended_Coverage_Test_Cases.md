# Project Management - Default Settings - Extended Coverage Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Default Settings** başlığı için extended coverage test case’lerini içerir.

---

## 2. Kapsamdaki Extended Coverage Senaryoları

- PM-DS-003
- PM-DS-006
- PM-DS-007
- PM-DS-008
- PM-DS-011
- PM-DS-012

---

## 3. Extended Coverage Test Cases

## TC-PM-DS-007
- **Test Case ID:** TC-PM-DS-007
- **Related Scenario ID:** PM-DS-003
- **Test Case Name:** Default columns board’da beklenen sırayla görünmeli
- **Priority:** P1
- **Type:** Functional / Consistency
- **Preconditions:** Default columns kaydedilmiş olmalı
- **Test Steps:**
  1. Board görünümünü aç
  2. Column sırasını kontrol et
- **Expected Result:** Sütunlar kaydedilen sırada görünmelidir
- **Automation Candidate:** UI automation

## TC-PM-DS-008
- **Test Case ID:** TC-PM-DS-008
- **Related Scenario ID:** PM-DS-006
- **Test Case Name:** Single subtask assignee ayarı etkinleştirilebilmeli
- **Priority:** P1
- **Type:** Functional
- **Preconditions:** Settings erişimi mevcut olmalı
- **Test Steps:**
  1. İlgili ayarı aç
  2. Save et
- **Expected Result:** Ayar aktif hale gelmelidir
- **Automation Candidate:** UI automation

## TC-PM-DS-009
- **Test Case ID:** TC-PM-DS-009
- **Related Scenario ID:** PM-DS-007
- **Test Case Name:** Tek subtask kuralı aktifken ikinci aktif atama engellenmeli
- **Priority:** P1
- **Type:** Functional / Negative
- **Preconditions:** Ayar aktif olmalı; kullanıcıya atanmış aktif subtask mevcut olmalı
- **Test Steps:**
  1. Aynı kullanıcıya ikinci aktif subtask atamayı dene
- **Expected Result:** İşlem engellenmeli veya kural gereği reddedilmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-DS-010
- **Test Case ID:** TC-PM-DS-010
- **Related Scenario ID:** PM-DS-008
- **Test Case Name:** Tek subtask kuralı kapalıyken birden fazla subtask ataması yapılabilmeli
- **Priority:** P1
- **Type:** Functional
- **Preconditions:** Ayar kapalı olmalı
- **Test Steps:**
  1. Aynı kullanıcıya birden fazla subtask ata
- **Expected Result:** Atamalar başarılı olmalıdır
- **Automation Candidate:** UI automation / API automation

## TC-PM-DS-011
- **Test Case ID:** TC-PM-DS-011
- **Related Scenario ID:** PM-DS-011
- **Test Case Name:** UI’da kaydedilen default settings API ile doğrulanabilmeli
- **Priority:** P1
- **Type:** Data Integrity / Consistency
- **Preconditions:** UI ve API erişimi hazır olmalı
- **Test Steps:**
  1. UI’dan default setting kaydet
  2. API ile proje ayarını sorgula
  3. Veriyi karşılaştır
- **Expected Result:** UI ve API verisi tutarlı olmalıdır
- **Automation Candidate:** API automation

## TC-PM-DS-012
- **Test Case ID:** TC-PM-DS-012
- **Related Scenario ID:** PM-DS-012
- **Test Case Name:** Boş veya hatalı default column girdisi kontrollü işlenmeli
- **Priority:** P2
- **Type:** Validation
- **Preconditions:** Default columns alanı düzenlenebilir olmalı
- **Test Steps:**
  1. Boş veya bozuk formatta column listesi gir
  2. Save et
  3. Sonucu gözlemle
- **Expected Result:** Sistem hata vermeden kontrollü davranmalı; gerçek ürün kuralı kayıt altına alınmalıdır
- **Automation Candidate:** Manual only