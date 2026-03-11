# Project Management - Default Settings - Core Regression Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Default Settings** başlığı için belirlenmiş çekirdek regresyon test case’lerini içerir.

---

## 2. Kapsamdaki Core Regression Senaryoları

- PM-DS-001
- PM-DS-002
- PM-DS-004
- PM-DS-005
- PM-DS-009
- PM-DS-010

---

## 3. Core Regression Test Cases

## TC-PM-DS-001
- **Test Case ID:** TC-PM-DS-001
- **Related Scenario ID:** PM-DS-001
- **Test Case Name:** Default columns kaydedilebilmeli
- **Priority:** P0
- **Type:** Functional
- **Preconditions:** Project Manager veya Administrator settings erişimine sahip olmalı
- **Test Steps:**
  1. Project settings’i aç
  2. Default columns alanına değer gir
  3. Kaydet
- **Test Data:**
  - Columns: `Backlog, Ready, Work in progress, Done`
- **Expected Result:** Default columns başarıyla kaydedilmelidir
- **Automation Candidate:** UI automation

## TC-PM-DS-002
- **Test Case ID:** TC-PM-DS-002
- **Related Scenario ID:** PM-DS-002
- **Test Case Name:** Virgülle ayrılmış default columns doğru parse edilmelidir
- **Priority:** P0
- **Type:** Validation / Functional
- **Preconditions:** Default columns alanı düzenlenebilir olmalı
- **Test Steps:**
  1. Virgülle ayrılmış sütun listesi gir
  2. Kaydet
  3. Board veya columns görünümünü kontrol et
- **Expected Result:** Her sütun ayrı column olarak oluşmalıdır
- **Automation Candidate:** UI automation

## TC-PM-DS-003
- **Test Case ID:** TC-PM-DS-003
- **Related Scenario ID:** PM-DS-004
- **Test Case Name:** Default categories kaydedilebilmeli
- **Priority:** P0
- **Type:** Functional
- **Preconditions:** Settings erişimi mevcut olmalı
- **Test Steps:**
  1. Project settings’i aç
  2. Default categories alanına veri gir
  3. Kaydet
- **Test Data:**
  - Categories: `Bug, Story, Improvement`
- **Expected Result:** Default categories başarıyla kaydedilmelidir
- **Automation Candidate:** UI automation

## TC-PM-DS-004
- **Test Case ID:** TC-PM-DS-004
- **Related Scenario ID:** PM-DS-005
- **Test Case Name:** Default categories otomatik oluşmalı
- **Priority:** P0
- **Type:** Functional
- **Preconditions:** Default categories tanımlanmış olmalı
- **Test Steps:**
  1. Category alanını aç
  2. Mevcut category listesini kontrol et
- **Expected Result:** Tanımlanan varsayılan categories sistemde bulunmalıdır
- **Automation Candidate:** UI automation / API automation

## TC-PM-DS-005
- **Test Case ID:** TC-PM-DS-005
- **Related Scenario ID:** PM-DS-009
- **Test Case Name:** Yetkisiz kullanıcı default settings değiştirememeli
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Project Member veya Viewer kullanıcı mevcut olmalı
- **Test Steps:**
  1. Yetkisiz kullanıcı ile giriş yap
  2. Project settings’e erişmeye çalış
  3. Default settings alanını değiştirmeyi dene
- **Expected Result:** Erişim veya değişiklik engellenmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-DS-006
- **Test Case ID:** TC-PM-DS-006
- **Related Scenario ID:** PM-DS-010
- **Test Case Name:** Default settings değişiklikleri kalıcı olmalı
- **Priority:** P0
- **Type:** Data Integrity
- **Preconditions:** Ayar değişikliği yapılmış olmalı
- **Test Steps:**
  1. Ayarı kaydet
  2. Sayfayı yenile
  3. Settings ekranını tekrar aç
- **Expected Result:** Kaydedilen default settings korunmalıdır
- **Automation Candidate:** UI automation