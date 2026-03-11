# Project Management - Create Project - Test Scenarios

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Create Project** fonksiyonu için test senaryo kapsamını tanımlar.

Bu başlığın amacı:
- yeni proje oluşturma davranışını doğrulamak
- proje tipi seçimine bağlı davranışları kontrol etmek
- rol/yetki bazlı oluşturma kurallarını doğrulamak
- oluşturma sırasında yapılan kopyalama işlemlerinin (permissions, actions, swimlanes, categories, tasks) doğru çalıştığını değerlendirmek
- negatif ve validasyon senaryolarını belirlemektir

Bu doküman henüz detaylı step-by-step test case dokümanı değildir.  
Bu doküman, bir sonraki aşamada üretilecek manual test case ve automation backlog çalışmaları için temel senaryo setidir.

---

## 2. Kapsam

Bu alt başlıkta aşağıdaki alanlar kapsanır:

- Private project oluşturma
- Team project oluşturma
- Rol bazlı proje oluşturma yetkileri
- Zorunlu alan ve isim validasyonları
- Duplicate/copy from existing project davranışları
- Oluşturma sonrası temel veri tutarlılığı
- Negatif ve authorization odaklı create project kontrolleri

Kapsam dışı:
- mevcut projenin düzenlenmesi
- proje görünüm ayarları
- default settings detayları
- proje silme
- create sonrası detaylı authorization davranışlarının tamamı

Bu alanlar ilgili alt başlıklarda ayrıca ele alınacaktır.

---

## 3. Test Scenario List

---

### PM-CP-001
- **Scenario ID:** PM-CP-001
- **Scenario Name:** Private project başarıyla oluşturulabilmeli
- **Type:** Functional
- **Priority:** P0
- **Preconditions:** Sistemde giriş yapılmış olmalı; proje oluşturma ekranına erişim mevcut olmalı
- **Test Description:** Kullanıcı yeni proje oluşturma ekranından Private Project tipi seçerek geçerli bir proje adı ile kayıt oluşturur
- **Expected Result:** Proje başarıyla oluşturulur ve proje listesinde görüntülenir
- **Automation Decision:** UI automation

---

### PM-CP-002
- **Scenario ID:** PM-CP-002
- **Scenario Name:** Administrator team project oluşturabilmeli
- **Type:** Authorization + Functional
- **Priority:** P0
- **Preconditions:** Administrator rolünde kullanıcı mevcut olmalı
- **Test Description:** Administrator kullanıcı yeni proje oluşturma ekranından Team Project seçerek proje oluşturur
- **Expected Result:** Team project başarıyla oluşturulur
- **Automation Decision:** UI automation / API automation

---

### PM-CP-003
- **Scenario ID:** PM-CP-003
- **Scenario Name:** Manager team project oluşturabilmeli
- **Type:** Authorization + Functional
- **Priority:** P0
- **Preconditions:** Manager rolünde kullanıcı mevcut olmalı
- **Test Description:** Manager kullanıcı Team Project oluşturmaya çalışır
- **Expected Result:** Yetki kuralları gereği team project başarıyla oluşturulmalıdır
- **Automation Decision:** UI automation / API automation

---

### PM-CP-004
- **Scenario ID:** PM-CP-004
- **Scenario Name:** Normal user team project oluşturamamalı
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** User rolünde kullanıcı mevcut olmalı
- **Test Description:** Normal user Team Project oluşturmaya çalışır
- **Expected Result:** İşlem engellenir; team project oluşturulamaz
- **Automation Decision:** UI automation / API automation

---

### PM-CP-005
- **Scenario ID:** PM-CP-005
- **Scenario Name:** Normal user private project oluşturabilmeli
- **Type:** Authorization + Functional
- **Priority:** P0
- **Preconditions:** User rolünde kullanıcı mevcut olmalı
- **Test Description:** Normal user geçerli bir isim ile Private Project oluşturur
- **Expected Result:** Private project başarıyla oluşturulur
- **Automation Decision:** UI automation

---

### PM-CP-006
- **Scenario ID:** PM-CP-006
- **Scenario Name:** Proje adı zorunlu olmalı
- **Type:** Validation / Negative
- **Priority:** P0
- **Preconditions:** Proje oluşturma ekranı açık olmalı
- **Test Description:** Kullanıcı proje adı alanını boş bırakıp oluşturma işlemini dener
- **Expected Result:** Kayıt işlemi tamamlanmaz; validasyon mesajı gösterilir
- **Automation Decision:** UI automation

---

### PM-CP-007
- **Scenario ID:** PM-CP-007
- **Scenario Name:** Sadece boşluk karakterlerinden oluşan proje adı kabul edilmemeli
- **Type:** Validation / Negative
- **Priority:** P1
- **Preconditions:** Proje oluşturma ekranı açık olmalı
- **Test Description:** Kullanıcı proje adı alanına yalnızca boşluk karakterleri girerek kayıt oluşturmaya çalışır
- **Expected Result:** Proje oluşturulmaz; alan geçersiz kabul edilir
- **Automation Decision:** UI automation

---

### PM-CP-008
- **Scenario ID:** PM-CP-008
- **Scenario Name:** Çok uzun proje adı sistem tarafından doğru işlenmeli
- **Type:** Validation / Boundary
- **Priority:** P1
- **Preconditions:** Maksimum karakter sınırı bilinmeli ya da keşif testi ile ölçülmeli
- **Test Description:** Kullanıcı izin verilen üst sınıra yakın veya üstünde proje adı ile kayıt oluşturmayı dener
- **Expected Result:** Sistem kontrollü şekilde davranmalıdır; geçerli ise kaydetmeli, geçersiz ise anlamlı hata vermeli; sistem hatası oluşmamalıdır
- **Automation Decision:** Manual only

---

### PM-CP-009
- **Scenario ID:** PM-CP-009
- **Scenario Name:** Aynı isimle ikinci proje oluşturma davranışı doğrulanmalı
- **Type:** Functional / Validation
- **Priority:** P1
- **Preconditions:** Aynı isimde mevcut bir proje bulunmalı
- **Test Description:** Kullanıcı mevcut proje adıyla aynı isimde yeni proje oluşturmaya çalışır
- **Expected Result:** Ürünün gerçek davranışı doğrulanmalı; duplicate isim kabul ediliyorsa kontrollü kabul edilmeli, engelleniyorsa anlamlı mesaj verilmelidir
- **Automation Decision:** Manual only

---

### PM-CP-010
- **Scenario ID:** PM-CP-010
- **Scenario Name:** Başka projeden permissions kopyalanarak proje oluşturulabilmeli
- **Type:** Functional / Data Integrity
- **Priority:** P1
- **Preconditions:** Kopyalanabilir izin yapısına sahip kaynak proje mevcut olmalı
- **Test Description:** Yeni proje oluşturulurken kaynak projeden permissions kopyalama seçeneği işaretlenir
- **Expected Result:** Yeni projede kaynak projedeki izin yapısı beklenen şekilde oluşur
- **Automation Decision:** API automation

---

### PM-CP-011
- **Scenario ID:** PM-CP-011
- **Scenario Name:** Başka projeden automatic actions kopyalanarak proje oluşturulabilmeli
- **Type:** Functional / Data Integrity
- **Priority:** P1
- **Preconditions:** Automatic action tanımlı kaynak proje mevcut olmalı
- **Test Description:** Yeni proje oluşturulurken kaynak projeden actions kopyalanır
- **Expected Result:** Yeni projede ilgili automatic actions kayıtları oluşmuş olmalıdır
- **Automation Decision:** API automation

---

### PM-CP-012
- **Scenario ID:** PM-CP-012
- **Scenario Name:** Başka projeden swimlanes kopyalanarak proje oluşturulabilmeli
- **Type:** Functional / Data Integrity
- **Priority:** P1
- **Preconditions:** Birden fazla swimlane içeren kaynak proje mevcut olmalı
- **Test Description:** Yeni proje oluşturulurken swimlanes kopyalama seçilir
- **Expected Result:** Yeni projede kaynakla uyumlu swimlane yapısı oluşur
- **Automation Decision:** API automation

---

### PM-CP-013
- **Scenario ID:** PM-CP-013
- **Scenario Name:** Başka projeden categories kopyalanarak proje oluşturulabilmeli
- **Type:** Functional / Data Integrity
- **Priority:** P1
- **Preconditions:** Category tanımlı kaynak proje mevcut olmalı
- **Test Description:** Yeni proje oluşturulurken categories kopyalama seçilir
- **Expected Result:** Yeni projede ilgili kategoriler oluşur
- **Automation Decision:** API automation

---

### PM-CP-014
- **Scenario ID:** PM-CP-014
- **Scenario Name:** Başka projeden tasks kopyalanarak proje oluşturulabilmeli
- **Type:** Functional / Data Integrity
- **Priority:** P1
- **Preconditions:** İçinde task bulunan kaynak proje mevcut olmalı
- **Test Description:** Yeni proje oluşturulurken tasks kopyalama seçilir
- **Expected Result:** Yeni projede task kayıtları beklenen şekilde oluşur
- **Automation Decision:** API automation

---

### PM-CP-015
- **Scenario ID:** PM-CP-015
- **Scenario Name:** Hiçbir kopyalama seçeneği işaretlenmeden temiz proje oluşturulabilmeli
- **Type:** Functional
- **Priority:** P1
- **Preconditions:** Proje oluşturma ekranı açık olmalı
- **Test Description:** Kullanıcı herhangi bir duplicate/copy seçeneği kullanmadan yeni proje oluşturur
- **Expected Result:** Proje boş veya standart başlangıç yapısıyla oluşturulur; seçilmemiş veriler taşınmaz
- **Automation Decision:** UI automation

---

### PM-CP-016
- **Scenario ID:** PM-CP-016
- **Scenario Name:** Kaynak proje seçilip kopyalama tipi seçilmezse temiz oluşturma davranışı korunmalı
- **Type:** Functional / Negative
- **Priority:** P2
- **Preconditions:** Kaynak proje mevcut olmalı
- **Test Description:** Kullanıcı kaynak proje seçer ancak hiçbir kopyalama seçeneğini işaretlemez
- **Expected Result:** Yeni proje oluşturulur ancak kopya veri taşınmaz
- **Automation Decision:** Not worth automating

---

### PM-CP-017
- **Scenario ID:** PM-CP-017
- **Scenario Name:** Private project oluşturulduğunda private erişim modeliyle başlamalı
- **Type:** Functional / Authorization
- **Priority:** P1
- **Preconditions:** Yeni private project oluşturulmuş olmalı
- **Test Description:** Oluşturulan private project’in temel erişim modeli gözlemlenir
- **Expected Result:** Private project, private erişim mantığına uygun başlangıç davranışı göstermelidir
- **Automation Decision:** Manual only

---

### PM-CP-018
- **Scenario ID:** PM-CP-018
- **Scenario Name:** Team project oluşturulduğunda ekip bazlı erişim modeliyle başlamalı
- **Type:** Functional / Authorization
- **Priority:** P1
- **Preconditions:** Yeni team project oluşturulmuş olmalı
- **Test Description:** Oluşturulan team project’in temel erişim modeli gözlemlenir
- **Expected Result:** Team project, ekip bazlı kullanım mantığına uygun başlangıç davranışı göstermelidir
- **Automation Decision:** Manual only

---

### PM-CP-019
- **Scenario ID:** PM-CP-019
- **Scenario Name:** UI üzerinden oluşturulan proje API tarafında doğrulanabilmeli
- **Type:** Data Integrity / Consistency
- **Priority:** P0
- **Preconditions:** UI üzerinden oluşturulmuş test projesi mevcut olmalı
- **Test Description:** UI ile oluşturulan proje API üzerinden sorgulanarak temel alanları doğrulanır
- **Expected Result:** UI ve API verileri tutarlı olmalıdır
- **Automation Decision:** API automation

---

### PM-CP-020
- **Scenario ID:** PM-CP-020
- **Scenario Name:** Yetkisiz kullanıcı ile API üzerinden team project oluşturma engellenmeli
- **Type:** API / Authorization / Negative
- **Priority:** P0
- **Preconditions:** Yetkisiz user API kimlik bilgileri hazır olmalı
- **Test Description:** Normal user API üzerinden team project oluşturmaya çalışır
- **Expected Result:** İşlem reddedilir ve proje oluşturulmaz
- **Automation Decision:** API automation

---

## 4. Coverage Classification

### 4.1 Core Regression
Aşağıdaki senaryolar create project fonksiyonu için çekirdek regresyon seti olarak değerlendirilir:

- PM-CP-001
- PM-CP-002
- PM-CP-003
- PM-CP-004
- PM-CP-005
- PM-CP-006
- PM-CP-019
- PM-CP-020

### 4.2 Extended Coverage
Aşağıdaki senaryolar ikinci aşama kapsam genişletme seti olarak ele alınır:

- PM-CP-007
- PM-CP-010
- PM-CP-011
- PM-CP-012
- PM-CP-013
- PM-CP-014
- PM-CP-015

### 4.3 Exploratory / Manual Discovery
Aşağıdaki senaryolar ürün davranışını keşfetmek veya business rule netleştirmek için öncelikle manuel değerlendirilecektir:

- PM-CP-008
- PM-CP-009
- PM-CP-017
- PM-CP-018

### 4.4 Low Value / Optional
Aşağıdaki senaryo düşük risk ve düşük tekrar değeri nedeniyle opsiyonel tutulur:

- PM-CP-016

---

## 5. Automation Strategy Summary

Bu alt başlık için önerilen otomasyon yaklaşımı:

### UI Automation için uygun senaryolar
- PM-CP-001
- PM-CP-002
- PM-CP-003
- PM-CP-004
- PM-CP-005
- PM-CP-006
- PM-CP-007
- PM-CP-015

### API Automation için uygun senaryolar
- PM-CP-002
- PM-CP-003
- PM-CP-004
- PM-CP-010
- PM-CP-011
- PM-CP-012
- PM-CP-013
- PM-CP-014
- PM-CP-019
- PM-CP-020

### Manual Only
- PM-CP-008
- PM-CP-009
- PM-CP-017
- PM-CP-018

### Not Worth Automating
- PM-CP-016
