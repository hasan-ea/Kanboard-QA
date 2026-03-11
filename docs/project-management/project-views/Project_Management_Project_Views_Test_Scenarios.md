# Project Management - Project Views - Test Scenarios

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Project Views** fonksiyonu için test senaryo kapsamını tanımlar.

Bu başlığın amacı:
- proje görevlerinin farklı görünümlerde doğru gösterildiğini doğrulamak
- Board, Calendar, List ve Gantt görünümlerinin erişilebilirliğini ve doğruluğunu kontrol etmek
- view değişiminin veri kaybına yol açmadığını değerlendirmek
- filter/search alanının tüm görünümlerde tutarlı çalıştığını gözlemlemektir

Bu doküman detaylı test case dokümanı değildir.

---

## 2. Kapsam

Bu alt başlıkta aşağıdaki alanlar kapsanır:

- Board görünümü
- Calendar görünümü
- List görünümü
- Gantt görünümü
- Görünümler arası geçiş
- Search/filter etkisinin tüm view’larda yansıması
- Board üzerinde drag & drop
- Yetkiye bağlı görünüm erişimi
- View değişiminden sonra veri tutarlılığı

Kapsam dışı:
- task create/update detayları
- reports
- public access / feeds
- project settings değişiklikleri

---

## 3. Test Scenario List

### PM-PV-001
- **Scenario ID:** PM-PV-001
- **Scenario Name:** Project board görünümü açılabilmeli
- **Type:** Functional
- **Priority:** P0
- **Preconditions:** Görev içeren proje mevcut olmalı
- **Test Description:** Kullanıcı projeyi board view’da açar
- **Expected Result:** Board görünümü başarıyla yüklenmeli ve task’lar sütunlar altında görünmelidir
- **Automation Decision:** UI automation

### PM-PV-002
- **Scenario ID:** PM-PV-002
- **Scenario Name:** Project list görünümü açılabilmeli
- **Type:** Functional
- **Priority:** P0
- **Preconditions:** Görev içeren proje mevcut olmalı
- **Test Description:** Kullanıcı list view’a geçer
- **Expected Result:** Görevler liste görünümünde yüklenmelidir
- **Automation Decision:** UI automation

### PM-PV-003
- **Scenario ID:** PM-PV-003
- **Scenario Name:** Project calendar görünümü açılabilmeli
- **Type:** Functional
- **Priority:** P1
- **Preconditions:** Due date içeren task’lar mevcut olmalı
- **Test Description:** Kullanıcı calendar view’a geçer
- **Expected Result:** Takvim görünümü açılmalı ve tarihli task’lar uygun günlerde görünmelidir
- **Automation Decision:** UI automation

### PM-PV-004
- **Scenario ID:** PM-PV-004
- **Scenario Name:** Project gantt görünümü açılabilmeli
- **Type:** Functional
- **Priority:** P1
- **Preconditions:** Start/end date veya planlı task verisi olan proje mevcut olmalı
- **Test Description:** Kullanıcı gantt view’a geçer
- **Expected Result:** Gantt görünümü açılmalı ve task/project timeline verisi görüntülenmelidir
- **Automation Decision:** UI automation

### PM-PV-005
- **Scenario ID:** PM-PV-005
- **Scenario Name:** View değişimi görev verisini değiştirmemeli
- **Type:** Data Integrity
- **Priority:** P0
- **Preconditions:** Birden fazla task içeren proje mevcut olmalı
- **Test Description:** Kullanıcı board, list, calendar ve gantt arasında geçiş yapar
- **Expected Result:** View değişimi yalnız sunumu değiştirmeli; veri kaybı veya task sayısı sapması olmamalıdır
- **Automation Decision:** UI automation

### PM-PV-006
- **Scenario ID:** PM-PV-006
- **Scenario Name:** Search/filter sonucu board view’da doğru yansımalı
- **Type:** Functional / Search
- **Priority:** P0
- **Preconditions:** Filtrelenebilir çeşitlilikte task verisi olmalı
- **Test Description:** Kullanıcı arama/filter uygular ve board görünümünü kontrol eder
- **Expected Result:** Yalnız filtreye uyan task’lar görünmelidir
- **Automation Decision:** UI automation

### PM-PV-007
- **Scenario ID:** PM-PV-007
- **Scenario Name:** Search/filter sonucu list view’da doğru yansımalı
- **Type:** Functional / Search
- **Priority:** P1
- **Preconditions:** Filtrelenebilir task verisi olmalı
- **Test Description:** Kullanıcı arama/filter uygular ve list görünümünü kontrol eder
- **Expected Result:** Liste görünümünde yalnız beklenen task’lar görünmelidir
- **Automation Decision:** UI automation

### PM-PV-008
- **Scenario ID:** PM-PV-008
- **Scenario Name:** Search/filter sonucu calendar view’da doğru yansımalı
- **Type:** Functional / Search
- **Priority:** P1
- **Preconditions:** Due date içeren ve filtrelenebilir task verisi mevcut olmalı
- **Test Description:** Kullanıcı arama/filter uygular ve calendar view’ı kontrol eder
- **Expected Result:** Takvimde yalnız filtreye uyan task’lar görünmelidir
- **Automation Decision:** UI automation

### PM-PV-009
- **Scenario ID:** PM-PV-009
- **Scenario Name:** Search/filter sonucu gantt view’da doğru yansımalı
- **Type:** Functional / Search
- **Priority:** P1
- **Preconditions:** Gantt’a uygun task verisi olmalı
- **Test Description:** Kullanıcı arama/filter uygular ve gantt görünümünü kontrol eder
- **Expected Result:** Gantt’ta yalnız filtreye uyan öğeler görünmelidir
- **Automation Decision:** UI automation

### PM-PV-010
- **Scenario ID:** PM-PV-010
- **Scenario Name:** Board görünümünde task sütunlar arasında drag & drop ile taşınabilmeli
- **Type:** Functional
- **Priority:** P0
- **Preconditions:** Board görünümü açık; hareket ettirilebilir task mevcut olmalı
- **Test Description:** Kullanıcı task’ı bir sütundan başka sütuna taşır
- **Expected Result:** Task yeni sütuna geçmeli ve durum/column bilgisi güncellenmelidir
- **Automation Decision:** UI automation

### PM-PV-011
- **Scenario ID:** PM-PV-011
- **Scenario Name:** Drag & drop sonrası list görünümünde yeni column bilgisi görünmeli
- **Type:** Consistency
- **Priority:** P1
- **Preconditions:** Board’da task taşınmış olmalı
- **Test Description:** Kullanıcı list görünümüne geçer
- **Expected Result:** Task’ın yeni column bilgisi listede doğru görünmelidir
- **Automation Decision:** UI automation

### PM-PV-012
- **Scenario ID:** PM-PV-012
- **Scenario Name:** Viewer board ve task’ları görüntüleyebilmeli
- **Type:** Authorization
- **Priority:** P1
- **Preconditions:** Viewer atanmış kullanıcı ve team project mevcut olmalı
- **Test Description:** Viewer ile proje view’larına erişim denenir
- **Expected Result:** Viewer read-only olarak board/tasks görüntüleyebilmelidir
- **Automation Decision:** UI automation

### PM-PV-013
- **Scenario ID:** PM-PV-013
- **Scenario Name:** Viewer drag & drop yapamamalı
- **Type:** Authorization / Negative
- **Priority:** P1
- **Preconditions:** Viewer atanmış kullanıcı ve board görünümü mevcut olmalı
- **Test Description:** Viewer task taşıma girişiminde bulunur
- **Expected Result:** Task hareket ettirilememelidir
- **Automation Decision:** UI automation

### PM-PV-014
- **Scenario ID:** PM-PV-014
- **Scenario Name:** Member board kullanabilmeli
- **Type:** Authorization / Functional
- **Priority:** P1
- **Preconditions:** Member atanmış kullanıcı mevcut olmalı
- **Test Description:** Member board görünümüne erişir ve temel kullanım davranışı gözlemlenir
- **Expected Result:** Member board’u kullanabilmelidir
- **Automation Decision:** UI automation

### PM-PV-015
- **Scenario ID:** PM-PV-015
- **Scenario Name:** Gantt erişimi member ve viewer için ürün davranışına göre doğrulanmalı
- **Type:** Authorization / Validation
- **Priority:** P1
- **Preconditions:** Member ve viewer kullanıcılar hazır olmalı
- **Test Description:** Member ve viewer gantt görünümüne erişmeye çalışır
- **Expected Result:** Gerçek ürün davranışı netleştirilmelidir; erişim izin/engel durumu kayıt altına alınmalıdır
- **Automation Decision:** Manual only

### PM-PV-016
- **Scenario ID:** PM-PV-016
- **Scenario Name:** Gelişmiş arama sözdizimi view sonuçlarını doğru filtrelemeli
- **Type:** Search / Functional
- **Priority:** P1
- **Preconditions:** Gelişmiş aramaya uygun task verisi mevcut olmalı
- **Test Description:** Kullanıcı advanced syntax ile sorgu çalıştırır
- **Expected Result:** Sorgu sonucu aktif view’da doğru yansımalıdır
- **Automation Decision:** UI automation

### PM-PV-017
- **Scenario ID:** PM-PV-017
- **Scenario Name:** View değişimi sonrası aktif filtre korunmalı
- **Type:** Functional / UX
- **Priority:** P2
- **Preconditions:** Aktif filtre uygulanmış proje görünümü mevcut olmalı
- **Test Description:** Kullanıcı view değiştirir
- **Expected Result:** Filtre yeni görünümde de korunmalı veya ürünün gerçek davranışı tutarlı olmalıdır
- **Automation Decision:** Manual only

### PM-PV-018
- **Scenario ID:** PM-PV-018
- **Scenario Name:** Board drag & drop sonucu API tarafında doğrulanabilmeli
- **Type:** Data Integrity / Consistency
- **Priority:** P1
- **Preconditions:** UI ve API erişimi hazır olmalı
- **Test Description:** UI’da task taşınır, ardından API ile task column bilgisi sorgulanır
- **Expected Result:** UI ve API verisi tutarlı olmalıdır
- **Automation Decision:** API automation

---

## 4. Coverage Classification

### 4.1 Core Regression
- PM-PV-001
- PM-PV-002
- PM-PV-005
- PM-PV-006
- PM-PV-010

### 4.2 Extended Coverage
- PM-PV-003
- PM-PV-004
- PM-PV-007
- PM-PV-008
- PM-PV-009
- PM-PV-011
- PM-PV-012
- PM-PV-013
- PM-PV-014
- PM-PV-016
- PM-PV-018

### 4.3 Exploratory / Manual Discovery
- PM-PV-015
- PM-PV-017

### 4.4 Low Value / Optional
Bu başlık için ayrı low value seti ayrılmamıştır.