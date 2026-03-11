# Project Management - Remove Project - Extended Coverage Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Remove Project** başlığı için extended coverage test case’lerini içerir.

---

## 2. Kapsamdaki Extended Coverage Senaryoları

- PM-RP-007
- PM-RP-008
- PM-RP-010

---

## 3. Extended Coverage Test Cases

## TC-PM-RP-008
- **Test Case ID:** TC-PM-RP-008
- **Related Scenario ID:** PM-RP-007
- **Test Case Name:** Remove confirmation onaylanmadan proje silinmemeli
- **Priority:** P1
- **Type:** UX / Negative
- **Preconditions:** Remove akışı açılabilmeli
- **Test Steps:**
  1. Remove akışını başlat
  2. İptal et veya onay vermeden çık
  3. Proje varlığını kontrol et
- **Expected Result:** Proje silinmemelidir
- **Automation Candidate:** UI automation

## TC-PM-RP-009
- **Test Case ID:** TC-PM-RP-009
- **Related Scenario ID:** PM-RP-008
- **Test Case Name:** UI ile silinen proje API tarafında bulunmamalı
- **Priority:** P1
- **Type:** Data Integrity / Consistency
- **Preconditions:** UI ve API erişimi hazır olmalı
- **Test Steps:**
  1. Projeyi UI ile sil
  2. API ile projeyi sorgula
- **Expected Result:** Proje API tarafında bulunmamalıdır
- **Automation Candidate:** API automation

## TC-PM-RP-010
- **Test Case ID:** TC-PM-RP-010
- **Related Scenario ID:** PM-RP-010
- **Test Case Name:** Remove sonrası eski proje URL’si açılmamalı
- **Priority:** P1
- **Type:** Functional / Negative
- **Preconditions:** Proje silinmiş olmalı
- **Test Steps:**
  1. Silinen projenin eski URL’sine git
- **Expected Result:** Proje bulunamamalı veya erişim başarısız olmalıdır
- **Automation Candidate:** UI automation