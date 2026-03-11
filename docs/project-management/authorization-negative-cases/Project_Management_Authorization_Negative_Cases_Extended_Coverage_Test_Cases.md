# Project Management - Authorization / Negative Cases - Extended Coverage Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Authorization / Negative Cases** başlığı için extended coverage test case’lerini içerir.

---

## 2. Kapsamdaki Extended Coverage Senaryoları

- PM-AN-008
- PM-AN-012
- PM-AN-013

---

## 3. Extended Coverage Test Cases

## TC-PM-AN-013
- **Test Case ID:** TC-PM-AN-013
- **Related Scenario ID:** PM-AN-008
- **Test Case Name:** Viewer board/task üzerinde write aksiyonu yapamamalı
- **Priority:** P1
- **Type:** Authorization / Negative
- **Preconditions:** Viewer atanmış kullanıcı ve task verisi mevcut olmalı
- **Test Steps:**
  1. Viewer ile giriş yap
  2. Task create veya update dene
  3. Drag & drop dene
- **Expected Result:** Tüm write aksiyonları engellenmelidir
- **Automation Candidate:** UI automation

## TC-PM-AN-014
- **Test Case ID:** TC-PM-AN-014
- **Related Scenario ID:** PM-AN-012
- **Test Case Name:** Project Member default settings değiştirememeli
- **Priority:** P1
- **Type:** Authorization / Negative
- **Preconditions:** Member atanmış kullanıcı ve proje mevcut olmalı
- **Test Steps:**
  1. Member ile giriş yap
  2. Default settings alanını açmayı dene
  3. Ayar değiştirmeyi dene
- **Expected Result:** İşlem reddedilmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-AN-015
- **Test Case ID:** TC-PM-AN-015
- **Related Scenario ID:** PM-AN-013
- **Test Case Name:** Project Viewer default settings değiştirememeli
- **Priority:** P1
- **Type:** Authorization / Negative
- **Preconditions:** Viewer atanmış kullanıcı ve proje mevcut olmalı
- **Test Steps:**
  1. Viewer ile giriş yap
  2. Default settings alanını açmayı dene
  3. Ayar değiştirmeyi dene
- **Expected Result:** İşlem reddedilmelidir
- **Automation Candidate:** UI automation / API automation