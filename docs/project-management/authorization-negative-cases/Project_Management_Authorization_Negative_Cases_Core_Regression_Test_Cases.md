# Project Management - Authorization / Negative Cases - Core Regression Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Authorization / Negative Cases** başlığı için çekirdek regresyon test case’lerini içerir.

---

## 2. Kapsamdaki Core Regression Senaryoları

- PM-AN-001
- PM-AN-002
- PM-AN-003
- PM-AN-004
- PM-AN-005
- PM-AN-006
- PM-AN-007
- PM-AN-009
- PM-AN-010
- PM-AN-011
- PM-AN-014
- PM-AN-015

---

## 3. Core Regression Test Cases

## TC-PM-AN-001
- **Test Case ID:** TC-PM-AN-001
- **Related Scenario ID:** PM-AN-001
- **Test Case Name:** User team project oluşturamamalı
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Application role `User` hesabı hazır olmalı
- **Test Steps:**
  1. User ile giriş yap
  2. Create project ekranına git
  3. Team project oluşturmayı dene
- **Expected Result:** İşlem engellenmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-AN-002
- **Test Case ID:** TC-PM-AN-002
- **Related Scenario ID:** PM-AN-002
- **Test Case Name:** Project Member project settings değiştirememeli
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Member atanmış kullanıcı ve team project mevcut olmalı
- **Test Steps:**
  1. Member ile giriş yap
  2. Project settings’e gitmeyi dene
  3. Ayar güncellemesi dene
- **Expected Result:** Erişim veya update engellenmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-AN-003
- **Test Case ID:** TC-PM-AN-003
- **Related Scenario ID:** PM-AN-003
- **Test Case Name:** Project Viewer project settings değiştirememeli
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Viewer atanmış kullanıcı ve team project mevcut olmalı
- **Test Steps:**
  1. Viewer ile giriş yap
  2. Project settings’e gitmeyi dene
- **Expected Result:** Erişim veya update engellenmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-AN-004
- **Test Case ID:** TC-PM-AN-004
- **Related Scenario ID:** PM-AN-004
- **Test Case Name:** Project Member permissions yönetememeli
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Member atanmış kullanıcı mevcut olmalı
- **Test Steps:**
  1. Member ile giriş yap
  2. Permissions ekranına gitmeyi dene
  3. User/role ekleme dene
- **Expected Result:** İşlem reddedilmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-AN-005
- **Test Case ID:** TC-PM-AN-005
- **Related Scenario ID:** PM-AN-005
- **Test Case Name:** Project Viewer permissions yönetememeli
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Viewer atanmış kullanıcı mevcut olmalı
- **Test Steps:**
  1. Viewer ile giriş yap
  2. Permissions ekranını açmayı dene
- **Expected Result:** İşlem reddedilmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-AN-006
- **Test Case ID:** TC-PM-AN-006
- **Related Scenario ID:** PM-AN-006
- **Test Case Name:** Project Member remove project yapamamalı
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Member atanmış kullanıcı ve hedef proje mevcut olmalı
- **Test Steps:**
  1. Member ile giriş yap
  2. Remove project akışını dene
- **Expected Result:** Silme işlemi yapılamamalıdır
- **Automation Candidate:** UI automation / API automation

## TC-PM-AN-007
- **Test Case ID:** TC-PM-AN-007
- **Related Scenario ID:** PM-AN-007
- **Test Case Name:** Project Viewer remove project yapamamalı
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Viewer atanmış kullanıcı ve hedef proje mevcut olmalı
- **Test Steps:**
  1. Viewer ile giriş yap
  2. Remove project akışını dene
- **Expected Result:** Silme işlemi yapılamamalıdır
- **Automation Candidate:** UI automation / API automation

## TC-PM-AN-008
- **Test Case ID:** TC-PM-AN-008
- **Related Scenario ID:** PM-AN-009
- **Test Case Name:** Yetkisiz kullanıcı private project’e erişememeli
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Private project ve ilgisiz kullanıcı hazır olmalı
- **Test Steps:**
  1. Yetkisiz kullanıcı ile giriş yap
  2. Private project’e erişmeyi dene
- **Expected Result:** Erişim reddedilmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-AN-009
- **Test Case ID:** TC-PM-AN-009
- **Related Scenario ID:** PM-AN-010
- **Test Case Name:** Yetkisiz kullanıcı API üzerinden project settings değiştirememeli
- **Priority:** P0
- **Type:** API / Authorization / Negative
- **Preconditions:** Yetkisiz user API credentials ve hedef proje mevcut olmalı
- **Test Steps:**
  1. Yetkisiz kullanıcı ile authenticate ol
  2. Settings update isteği gönder
  3. Response’u kontrol et
- **Expected Result:** İstek reddedilmelidir
- **Automation Candidate:** API automation

## TC-PM-AN-010
- **Test Case ID:** TC-PM-AN-010
- **Related Scenario ID:** PM-AN-011
- **Test Case Name:** Yetkisiz kullanıcı API üzerinden permissions değiştirememeli
- **Priority:** P0
- **Type:** API / Authorization / Negative
- **Preconditions:** Yetkisiz user API credentials ve team project mevcut olmalı
- **Test Steps:**
  1. Yetkisiz kullanıcı ile authenticate ol
  2. Permissions değiştirme isteği gönder
  3. Response’u kontrol et
- **Expected Result:** İstek reddedilmelidir
- **Automation Candidate:** API automation

## TC-PM-AN-011
- **Test Case ID:** TC-PM-AN-011
- **Related Scenario ID:** PM-AN-014
- **Test Case Name:** Member private/team type değişikliği yapamamalı
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Member atanmış kullanıcı ve hedef proje mevcut olmalı
- **Test Steps:**
  1. Member ile giriş yap
  2. Type/private flag değiştirmeyi dene
- **Expected Result:** İşlem reddedilmelidir
- **Automation Candidate:** UI automation / API automation

## TC-PM-AN-012
- **Test Case ID:** TC-PM-AN-012
- **Related Scenario ID:** PM-AN-015
- **Test Case Name:** Viewer private/team type değişikliği yapamamalı
- **Priority:** P0
- **Type:** Authorization / Negative
- **Preconditions:** Viewer atanmış kullanıcı ve hedef proje mevcut olmalı
- **Test Steps:**
  1. Viewer ile giriş yap
  2. Type/private flag değiştirmeyi dene
- **Expected Result:** İşlem reddedilmelidir
- **Automation Candidate:** UI automation / API automation