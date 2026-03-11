# Project Management - Remove Project - Core Regression Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Remove Project** başlığı için çekirdek regresyon test case’lerini içerir.

---

## 2. Kapsamdaki Core Regression Senaryoları

- PM-RP-001
- PM-RP-002
- PM-RP-003
- PM-RP-004
- PM-RP-005
- PM-RP-006
- PM-RP-009

---

## 3. Core Regression Test Cases

## TC-PM-RP-001
- **Test Case ID:** TC-PM-RP-001
- **Related Scenario ID:** PM-RP-001
- **Test Case Name:** Administrator projeyi silebilmeli
- **Priority:** P0
- **Type:** Functional / Authorization
- **Preconditions:** Hedef proje mevcut olmalı
- **Test Steps:**
  1. Administrator ile giriş yap
  2. Hedef projeyi aç
  3. Remove project akışını başlat
  4. Onayla
- **Expected Result:** Proje başarıyla silinmelidir
- **Automation Candidate:** UI automation

## TC-PM-RP-002
- **Test Case ID:** TC-PM-RP-002
- **Related Scenario ID:** PM-RP-002
- **Test Case Name:** Project Manager projeyi silebilmeli
- **Priority:** P0
- **Type:** Functional / Authorization
- **Preconditions:** PM rolüne sahip kullanıcı ve hedef proje mevcut olmalı
- **Test Steps:**
  1. PM ile giriş yap
  2. Hedef projeyi aç
  3. Remove akışını başlat
  4. Onayla
- **Expected Result:** Proje silinebilmelidir
- **Automation Candidate:** UI automation

## TC-PM-RP-003
- **Test Case ID:** TC-PM-RP-003
- **Related Scenario ID:** PM-RP-003
- **Test Case Name:** Project Member projeyi silememeli
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Member atanmış kullanıcı ve hedef proje mevcut olmalı
- **Test Steps:**
  1. Member ile giriş yap
  2. Projeyi aç
  3. Remove akışına erişmeye çalış
- **Expected Result:** Erişim veya silme işlemi engellenmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-RP-004
- **Test Case ID:** TC-PM-RP-004
- **Related Scenario ID:** PM-RP-004
- **Test Case Name:** Project Viewer projeyi silememeli
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Viewer atanmış kullanıcı ve hedef proje mevcut olmalı
- **Test Steps:**
  1. Viewer ile giriş yap
  2. Projeyi aç
  3. Remove akışına erişmeye çalış
- **Expected Result:** Erişim veya silme işlemi engellenmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-RP-005
- **Test Case ID:** TC-PM-RP-005
- **Related Scenario ID:** PM-RP-005
- **Test Case Name:** Remove edilen proje listede görünmemeli
- **Priority:** P0
- **Type:** Functional / Consistency
- **Preconditions:** Proje başarıyla silinmiş olmalı
- **Test Steps:**
  1. Projeyi sil
  2. Proje listesine dön
  3. Proje adını ara
- **Expected Result:** Silinen proje listede görünmemelidir
- **Automation Candidate:** UI automation

## TC-PM-RP-006
- **Test Case ID:** TC-PM-RP-006
- **Related Scenario ID:** PM-RP-006
- **Test Case Name:** Remove edilen projeye ait tüm task’lar da silinmeli
- **Priority:** P0
- **Type:** Data Integrity
- **Preconditions:** Task içeren proje silinmiş olmalı
- **Test Steps:**
  1. Silme öncesi task kayıtlarını not et
  2. Projeyi sil
  3. API veya ilgili sorgu ile task kayıtlarını kontrol et
- **Expected Result:** Projeye ait tüm task kayıtları silinmiş olmalıdır
- **Automation Candidate:** API automation

## TC-PM-RP-007
- **Test Case ID:** TC-PM-RP-007
- **Related Scenario ID:** PM-RP-009
- **Test Case Name:** Yetkisiz kullanıcı API üzerinden proje silememeli
- **Priority:** P0
- **Type:** API / Authorization / Negative
- **Preconditions:** Yetkisiz kullanıcı API credentials ve hedef proje mevcut olmalı
- **Test Steps:**
  1. Yetkisiz kullanıcı ile authenticate ol
  2. Remove project isteği gönder
  3. Response’u kontrol et
  4. Projenin varlığını doğrula
- **Expected Result:** İstek reddedilmeli ve proje silinmemelidir
- **Automation Candidate:** API automation