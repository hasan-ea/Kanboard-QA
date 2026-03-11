# Project Management - Remove Project - Test Scenarios

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Remove Project** fonksiyonu için test senaryo kapsamını tanımlar.

Bu başlığın amacı:
- proje silme yetkilerini doğrulamak
- remove işleminden sonra proje ve ilişkili task verisinin beklenen şekilde kaldırıldığını doğrulamak
- yanlış kullanıcıların proje silememesini kontrol etmektir

---

## 2. Kapsam

Bu alt başlıkta aşağıdaki alanlar kapsanır:

- Administrator remove project
- Project Manager remove project
- Member/Viewer remove project negatifleri
- Remove sonrası proje görünmezliği
- Remove sonrası tüm task’ların silinmesi
- API ve UI tutarlılığı
- Uyarı/confirmation davranışı

---

## 3. Test Scenario List

### PM-RP-001
- **Scenario ID:** PM-RP-001
- **Scenario Name:** Administrator projeyi silebilmeli
- **Type:** Functional / Authorization
- **Priority:** P0
- **Preconditions:** Hedef proje mevcut olmalı
- **Test Description:** Administrator remove işlemi yapar
- **Expected Result:** Proje başarıyla kaldırılmalıdır
- **Automation Decision:** UI automation

### PM-RP-002
- **Scenario ID:** PM-RP-002
- **Scenario Name:** Project Manager projeyi silebilmeli
- **Type:** Functional / Authorization
- **Priority:** P0
- **Preconditions:** Project Manager rolü ve hedef proje mevcut olmalı
- **Test Description:** PM remove işlemi yapar
- **Expected Result:** Proje kaldırılmalıdır
- **Automation Decision:** UI automation

### PM-RP-003
- **Scenario ID:** PM-RP-003
- **Scenario Name:** Project Member projeyi silememeli
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Member atanmış kullanıcı ve hedef proje mevcut olmalı
- **Test Description:** Member remove işlemine erişmeye çalışır
- **Expected Result:** Silme işlemi engellenmelidir
- **Automation Decision:** UI automation / API automation

### PM-RP-004
- **Scenario ID:** PM-RP-004
- **Scenario Name:** Project Viewer projeyi silememeli
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Viewer atanmış kullanıcı ve hedef proje mevcut olmalı
- **Test Description:** Viewer remove işlemine erişmeye çalışır
- **Expected Result:** Silme işlemi engellenmelidir
- **Automation Decision:** UI automation / API automation

### PM-RP-005
- **Scenario ID:** PM-RP-005
- **Scenario Name:** Remove edilen proje listede görünmemeli
- **Type:** Functional / Consistency
- **Priority:** P0
- **Preconditions:** Proje başarıyla silinmiş olmalı
- **Test Description:** Kullanıcı proje listesine döner
- **Expected Result:** Silinen proje görünmemelidir
- **Automation Decision:** UI automation

### PM-RP-006
- **Scenario ID:** PM-RP-006
- **Scenario Name:** Remove edilen projeye ait tüm task’lar da silinmeli
- **Type:** Data Integrity
- **Priority:** P0
- **Preconditions:** Task içeren proje remove edilmiş olmalı
- **Test Description:** Silme sonrası task kayıtları kontrol edilir
- **Expected Result:** Projeye ait tüm task’lar kaldırılmış olmalıdır
- **Automation Decision:** API automation

### PM-RP-007
- **Scenario ID:** PM-RP-007
- **Scenario Name:** Remove confirmation olmadan silme gerçekleşmemeli
- **Type:** UX / Negative
- **Priority:** P1
- **Preconditions:** Proje remove ekranı açık olmalı
- **Test Description:** Kullanıcı silme akışını iptal eder veya onaylamaz
- **Expected Result:** Proje silinmemelidir
- **Automation Decision:** UI automation

### PM-RP-008
- **Scenario ID:** PM-RP-008
- **Scenario Name:** Remove işlemi API tarafında da doğrulanabilmeli
- **Type:** Data Integrity / Consistency
- **Priority:** P1
- **Preconditions:** UI ve API erişimi hazır olmalı
- **Test Description:** UI ile proje silinir, API ile varlığı kontrol edilir
- **Expected Result:** Silinen proje API tarafında bulunmamalıdır
- **Automation Decision:** API automation

### PM-RP-009
- **Scenario ID:** PM-RP-009
- **Scenario Name:** Yetkisiz kullanıcı API üzerinden proje silememeli
- **Type:** API / Authorization / Negative
- **Priority:** P0
- **Preconditions:** Yetkisiz kullanıcı API erişimi ve hedef proje mevcut olmalı
- **Test Description:** Yetkisiz kullanıcı API ile remove isteği gönderir
- **Expected Result:** İstek reddedilmeli ve proje silinmemelidir
- **Automation Decision:** API automation

### PM-RP-010
- **Scenario ID:** PM-RP-010
- **Scenario Name:** Remove sonrası doğrudan URL erişimi başarısız olmalı
- **Type:** Functional / Negative
- **Priority:** P1
- **Preconditions:** Proje silinmiş olmalı
- **Test Description:** Eski proje URL’sine erişim denenir
- **Expected Result:** Proje bulunamamalı veya erişim başarısız olmalıdır
- **Automation Decision:** UI automation