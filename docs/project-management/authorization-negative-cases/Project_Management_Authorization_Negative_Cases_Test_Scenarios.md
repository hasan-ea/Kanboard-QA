# Project Management - Authorization / Negative Cases - Test Scenarios

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Authorization / Negative Cases** başlığı için test senaryo kapsamını tanımlar.

Bu başlığın amacı:
- project management kapsamındaki yetki ihlali ve negatif davranışları tek yerde toplamak
- application role ve project role kaynaklı erişim farklarını negatif perspektiften doğrulamak
- UI ve User API seviyesinde RBAC tutarlılığını değerlendirmektir

---

## 2. Kapsam

Bu alt başlıkta aşağıdaki alanlar kapsanır:

- Application role negatifleri
- Project role negatifleri
- Yetkisiz settings erişimi
- Yetkisiz permission management erişimi
- Yetkisiz remove project
- Yetkisiz type change
- Viewer read-only doğrulamaları
- User API RBAC negatifleri

Kapsam dışı:
- task-level ayrıntılı RBAC
- user management modülü
- app settings modülü

---

## 3. Test Scenario List

### PM-AN-001
- **Scenario ID:** PM-AN-001
- **Scenario Name:** User team project oluşturamamalı
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** User application role hazır olmalı
- **Test Description:** User team project oluşturmayı dener
- **Expected Result:** İşlem engellenmelidir
- **Automation Decision:** UI automation / API automation

### PM-AN-002
- **Scenario ID:** PM-AN-002
- **Scenario Name:** Project Member project settings değiştirememeli
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Member atanmış kullanıcı ve team project mevcut olmalı
- **Test Description:** Member project settings alanlarına erişmeye çalışır
- **Expected Result:** Erişim veya değişiklik engellenmelidir
- **Automation Decision:** UI automation / API automation

### PM-AN-003
- **Scenario ID:** PM-AN-003
- **Scenario Name:** Project Viewer project settings değiştirememeli
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Viewer atanmış kullanıcı ve team project mevcut olmalı
- **Test Description:** Viewer project settings’e erişmeye çalışır
- **Expected Result:** Erişim veya değişiklik engellenmelidir
- **Automation Decision:** UI automation / API automation

### PM-AN-004
- **Scenario ID:** PM-AN-004
- **Scenario Name:** Project Member project permissions yönetememeli
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Member atanmış kullanıcı mevcut olmalı
- **Test Description:** Member permissions ekranına erişir veya rol atamayı dener
- **Expected Result:** İşlem engellenmelidir
- **Automation Decision:** UI automation / API automation

### PM-AN-005
- **Scenario ID:** PM-AN-005
- **Scenario Name:** Project Viewer project permissions yönetememeli
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Viewer atanmış kullanıcı mevcut olmalı
- **Test Description:** Viewer permissions ekranına erişmeye çalışır
- **Expected Result:** İşlem engellenmelidir
- **Automation Decision:** UI automation / API automation

### PM-AN-006
- **Scenario ID:** PM-AN-006
- **Scenario Name:** Project Member remove project yapamamalı
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Member atanmış kullanıcı mevcut olmalı
- **Test Description:** Member remove akışını dener
- **Expected Result:** Proje silinememelidir
- **Automation Decision:** UI automation / API automation

### PM-AN-007
- **Scenario ID:** PM-AN-007
- **Scenario Name:** Project Viewer remove project yapamamalı
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Viewer atanmış kullanıcı mevcut olmalı
- **Test Description:** Viewer remove akışını dener
- **Expected Result:** Proje silinememelidir
- **Automation Decision:** UI automation / API automation

### PM-AN-008
- **Scenario ID:** PM-AN-008
- **Scenario Name:** Viewer task/board üzerinde write aksiyonu yapamamalı
- **Type:** Authorization / Negative
- **Priority:** P1
- **Preconditions:** Viewer atanmış kullanıcı ve task verisi mevcut olmalı
- **Test Description:** Viewer create/update/move aksiyonu dener
- **Expected Result:** Write aksiyonları engellenmelidir
- **Automation Decision:** UI automation

### PM-AN-009
- **Scenario ID:** PM-AN-009
- **Scenario Name:** Yetkisiz kullanıcı private project’e erişememeli
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Private project ve yetkisiz kullanıcı mevcut olmalı
- **Test Description:** Yetkisiz kullanıcı private project’e erişmeyi dener
- **Expected Result:** Erişim reddedilmelidir
- **Automation Decision:** UI automation / API automation

### PM-AN-010
- **Scenario ID:** PM-AN-010
- **Scenario Name:** Yetkisiz kullanıcı API üzerinden project settings değiştirememeli
- **Type:** API / Authorization / Negative
- **Priority:** P0
- **Preconditions:** Yetkisiz user API credentials mevcut olmalı
- **Test Description:** User API ile settings update denemesi yapılır
- **Expected Result:** İstek reddedilmelidir
- **Automation Decision:** API automation

### PM-AN-011
- **Scenario ID:** PM-AN-011
- **Scenario Name:** Yetkisiz kullanıcı API üzerinden permissions değiştirememeli
- **Type:** API / Authorization / Negative
- **Priority:** P0
- **Preconditions:** Yetkisiz user API credentials ve team project mevcut olmalı
- **Test Description:** API üzerinden user/group authorization değiştirme denemesi yapılır
- **Expected Result:** İstek reddedilmelidir
- **Automation Decision:** API automation

### PM-AN-012
- **Scenario ID:** PM-AN-012
- **Scenario Name:** Project Member default settings değiştirememeli
- **Type:** Authorization / Negative
- **Priority:** P1
- **Preconditions:** Member atanmış kullanıcı mevcut olmalı
- **Test Description:** Member default settings alanına müdahale etmeye çalışır
- **Expected Result:** İşlem engellenmelidir
- **Automation Decision:** UI automation / API automation

### PM-AN-013
- **Scenario ID:** PM-AN-013
- **Scenario Name:** Project Viewer default settings değiştirememeli
- **Type:** Authorization / Negative
- **Priority:** P1
- **Preconditions:** Viewer atanmış kullanıcı mevcut olmalı
- **Test Description:** Viewer default settings alanına müdahale etmeye çalışır
- **Expected Result:** İşlem engellenmelidir
- **Automation Decision:** UI automation / API automation

### PM-AN-014
- **Scenario ID:** PM-AN-014
- **Scenario Name:** Member private/team type değişikliği yapamamalı
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Member atanmış kullanıcı ve hedef proje mevcut olmalı
- **Test Description:** Member proje tipi değiştirmeyi dener
- **Expected Result:** İşlem reddedilmelidir
- **Automation Decision:** UI automation / API automation

### PM-AN-015
- **Scenario ID:** PM-AN-015
- **Scenario Name:** Viewer private/team type değişikliği yapamamalı
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Viewer atanmış kullanıcı ve hedef proje mevcut olmalı
- **Test Description:** Viewer proje tipi değiştirmeyi dener
- **Expected Result:** İşlem reddedilmelidir
- **Automation Decision:** UI automation / API automation