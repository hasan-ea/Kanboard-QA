# Project Management - Default Settings - Test Scenarios

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Default Settings** başlığı için test senaryo kapsamını tanımlar.

Bu başlığın amacı:
- proje ayarları altında tanımlanan varsayılan yapıların doğru çalıştığını doğrulamak
- default columns, default categories ve subtask assignment kuralı gibi ayarların proje davranışına etkisini kontrol etmektir

---

## 2. Kapsam

Bu alt başlıkta aşağıdaki alanlar kapsanır:

- Default columns tanımı
- Virgülle ayrılmış column isimlerinin işlenmesi
- Yeni proje veya proje ayarlarında default columns davranışı
- Default categories listesi
- Default category’lerin otomatik oluşumu
- “Bir kullanıcının aynı anda yalnızca bir subtask alması” ayarı
- Ayar değişikliklerinin kalıcılığı
- Yetki bazlı settings erişimi

Kapsam dışı:
- custom roles
- automatic actions
- public access
- columns/categories CRUD detayları

---

## 3. Test Scenario List

### PM-DS-001
- **Scenario ID:** PM-DS-001
- **Scenario Name:** Project settings altında default columns tanımlanabilmeli
- **Type:** Functional
- **Priority:** P0
- **Preconditions:** Project settings erişimi olan kullanıcı mevcut olmalı
- **Test Description:** Kullanıcı virgülle ayrılmış column listesi tanımlar
- **Expected Result:** Varsayılan sütun listesi kaydedilebilmelidir
- **Automation Decision:** UI automation

### PM-DS-002
- **Scenario ID:** PM-DS-002
- **Scenario Name:** Default columns virgülle ayrılmış isimlerden doğru parse edilmelidir
- **Type:** Validation / Functional
- **Priority:** P0
- **Preconditions:** Column listesi girilebilir olmalı
- **Test Description:** Kullanıcı birden fazla sütun adını virgülle ayırarak girer
- **Expected Result:** Her sütun ayrı kayıt olarak yorumlanmalıdır
- **Automation Decision:** UI automation

### PM-DS-003
- **Scenario ID:** PM-DS-003
- **Scenario Name:** Kaydedilen default columns proje içinde beklenen sırayla görünmeli
- **Type:** Functional / Consistency
- **Priority:** P1
- **Preconditions:** Default columns kaydedilmiş olmalı
- **Test Description:** Kullanıcı board’a döner ve sütun sırasını kontrol eder
- **Expected Result:** Kaydedilen sütunlar beklenen sırada görünmelidir
- **Automation Decision:** UI automation

### PM-DS-004
- **Scenario ID:** PM-DS-004
- **Scenario Name:** Default categories listesi tanımlanabilmeli
- **Type:** Functional
- **Priority:** P0
- **Preconditions:** Project settings erişimi mevcut olmalı
- **Test Description:** Kullanıcı varsayılan category listesi tanımlar
- **Expected Result:** Default categories kaydedilebilmelidir
- **Automation Decision:** UI automation

### PM-DS-005
- **Scenario ID:** PM-DS-005
- **Scenario Name:** Default categories proje içinde otomatik oluşmalı
- **Type:** Functional
- **Priority:** P0
- **Preconditions:** Default categories kaydedilmiş olmalı
- **Test Description:** Kullanıcı proje category alanlarını kontrol eder
- **Expected Result:** Tanımlanan categories otomatik oluşmuş olmalıdır
- **Automation Decision:** UI automation / API automation

### PM-DS-006
- **Scenario ID:** PM-DS-006
- **Scenario Name:** “Bir kullanıcının aynı anda yalnızca bir subtask alması” ayarı etkinleştirilebilmeli
- **Type:** Functional
- **Priority:** P1
- **Preconditions:** Project settings erişimi mevcut olmalı
- **Test Description:** Kullanıcı ilgili ayarı aktif eder
- **Expected Result:** Ayar başarıyla kaydedilmelidir
- **Automation Decision:** UI automation

### PM-DS-007
- **Scenario ID:** PM-DS-007
- **Scenario Name:** Single subtask assignee kuralı aktifken aynı kullanıcıya ikinci aktif subtask atanamamalı
- **Type:** Functional / Negative
- **Priority:** P1
- **Preconditions:** Ayar aktif olmalı; aynı kullanıcıya atanmış aktif subtask mevcut olmalı
- **Test Description:** Aynı kullanıcıya ikinci aktif subtask atanır
- **Expected Result:** İşlem engellenmeli veya ürün kuralına uygun davranış göstermelidir
- **Automation Decision:** UI automation / API automation

### PM-DS-008
- **Scenario ID:** PM-DS-008
- **Scenario Name:** Single subtask assignee kuralı kapalıyken aynı kullanıcıya birden fazla subtask atanabilmeli
- **Type:** Functional
- **Priority:** P1
- **Preconditions:** Ayar kapalı olmalı
- **Test Description:** Aynı kullanıcıya birden fazla aktif subtask atanır
- **Expected Result:** Atama başarılı olmalıdır
- **Automation Decision:** UI automation / API automation

### PM-DS-009
- **Scenario ID:** PM-DS-009
- **Scenario Name:** Yetkisiz kullanıcı project settings default alanlarını değiştirememeli
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** PM olmayan kullanıcı mevcut olmalı
- **Test Description:** Kullanıcı default settings alanını değiştirmeye çalışır
- **Expected Result:** Erişim veya update engellenmelidir
- **Automation Decision:** UI automation / API automation

### PM-DS-010
- **Scenario ID:** PM-DS-010
- **Scenario Name:** Default settings değişiklikleri sayfa yenileme sonrası kalıcı olmalı
- **Type:** Data Integrity
- **Priority:** P0
- **Preconditions:** Bir veya daha fazla default ayar güncellenmiş olmalı
- **Test Description:** Kullanıcı kaydedip sayfayı yeniler
- **Expected Result:** Kaydedilen varsayılan ayarlar korunmalıdır
- **Automation Decision:** UI automation

### PM-DS-011
- **Scenario ID:** PM-DS-011
- **Scenario Name:** UI’da kaydedilen default settings API tarafında doğrulanabilmeli
- **Type:** Data Integrity / Consistency
- **Priority:** P1
- **Preconditions:** UI ve API erişimi hazır olmalı
- **Test Description:** UI’dan ayar kaydedilir, API ile doğrulanır
- **Expected Result:** UI ve API verisi tutarlı olmalıdır
- **Automation Decision:** API automation

### PM-DS-012
- **Scenario ID:** PM-DS-012
- **Scenario Name:** Boş veya hatalı default column girdisi kontrollü işlenmeli
- **Type:** Validation
- **Priority:** P2
- **Preconditions:** Default columns alanı düzenlenebilir olmalı
- **Test Description:** Kullanıcı boş/hatalı format girer
- **Expected Result:** Sistem kontrollü hata veya tutarlı parsing davranışı göstermelidir
- **Automation Decision:** Manual only