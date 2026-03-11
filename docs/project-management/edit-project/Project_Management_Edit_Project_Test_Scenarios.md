# Project Management - Edit Project - Test Scenarios

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Edit Project** fonksiyonu için test senaryo kapsamını tanımlar.

Bu başlığın amacı:
- mevcut proje bilgilerinin güncellenme davranışını doğrulamak
- düzenleme sırasında uygulanan validasyon kurallarını kontrol etmek
- edit sonrası verinin kalıcılığını ve tutarlılığını değerlendirmek
- yetki bazlı düzenleme kurallarını doğrulamak
- UI ve API katmanları arasında update davranışının tutarlılığını gözlemlemektir

Bu doküman detaylı step-by-step test case dokümanı değildir.  
Bu doküman, bir sonraki aşamada üretilecek core regression test case ve extended coverage test case çalışmaları için temel senaryo setidir.

---

## 2. Kapsam

Bu alt başlıkta aşağıdaki alanlar kapsanır:

- Proje adının güncellenmesi
- Proje açıklamasının güncellenmesi
- Edit formunda mevcut bilgilerin ön yüklü gelmesi
- Zorunlu alan ve isim validasyonları
- Edit sonrası verinin kalıcılığı
- UI üzerinden yapılan update’in API tarafında doğrulanması
- Rol/yetki bazlı proje düzenleme davranışları
- Yetkisiz kullanıcıların update girişimleri
- Proje listesi ve detay ekranında güncel verinin görünmesi

Kapsam dışı:
- proje tipi / erişim davranışının kapsamlı kuralları
- proje görünüm ayarları
- varsayılan proje ayarları
- proje silme
- proje oluşturma
- authorization kurallarının tüm detayları

Bu alanlar ilgili alt başlıklarda ayrıca ele alınacaktır.

---

## 3. Test Scenario List

---

### PM-EP-001
- **Scenario ID:** PM-EP-001
- **Scenario Name:** Mevcut proje adı başarıyla güncellenebilmeli
- **Type:** Functional
- **Priority:** P0
- **Preconditions:** Sistemde düzenlenebilir bir proje mevcut olmalı; kullanıcı edit yetkisine sahip olmalı
- **Test Description:** Kullanıcı mevcut projeyi açar, proje adını geçerli yeni bir değer ile değiştirir ve kaydeder
- **Expected Result:** Proje adı başarıyla güncellenir ve sistem genelinde yeni ad görünür
- **Automation Decision:** UI automation

---

### PM-EP-002
- **Scenario ID:** PM-EP-002
- **Scenario Name:** Proje adı boş bırakıldığında güncelleme engellenmeli
- **Type:** Validation / Negative
- **Priority:** P0
- **Preconditions:** Düzenlenebilir bir proje mevcut olmalı
- **Test Description:** Kullanıcı proje adı alanını boş bırakıp update işlemini dener
- **Expected Result:** Güncelleme engellenir; kullanıcıya validasyon mesajı gösterilir
- **Automation Decision:** UI automation

---

### PM-EP-003
- **Scenario ID:** PM-EP-003
- **Scenario Name:** Sadece boşluk karakterlerinden oluşan proje adı ile güncelleme engellenmeli
- **Type:** Validation / Negative
- **Priority:** P1
- **Preconditions:** Düzenlenebilir bir proje mevcut olmalı
- **Test Description:** Kullanıcı proje adı alanına yalnızca boşluk karakterleri girerek update etmeye çalışır
- **Expected Result:** Güncelleme tamamlanmaz; geçerli validasyon davranışı gösterilir
- **Automation Decision:** UI automation

---

### PM-EP-004
- **Scenario ID:** PM-EP-004
- **Scenario Name:** Proje adı çok uzun olduğunda sistem kontrollü davranmalı
- **Type:** Validation / Boundary
- **Priority:** P1
- **Preconditions:** Düzenlenebilir bir proje mevcut olmalı; karakter sınırı biliniyor olmalı ya da keşif ile ölçülmeli
- **Test Description:** Kullanıcı proje adını üst sınıra yakın veya üstünde bir değer ile güncellemeye çalışır
- **Expected Result:** Sistem ya kontrollü olarak kabul eder ya da anlamlı validasyon hatası verir; sistem hatası oluşmaz
- **Automation Decision:** Manual only

---

### PM-EP-005
- **Scenario ID:** PM-EP-005
- **Scenario Name:** Proje adı eski değerden yeni değere kalıcı olarak güncellenmeli
- **Type:** Data Integrity
- **Priority:** P0
- **Preconditions:** Düzenlenebilir bir proje mevcut olmalı
- **Test Description:** Kullanıcı proje adını günceller, sayfayı yeniler veya projeye yeniden girer
- **Expected Result:** Güncellenen proje adı kalıcı olmalı; eski değer geri dönmemeli
- **Automation Decision:** UI automation

---

### PM-EP-006
- **Scenario ID:** PM-EP-006
- **Scenario Name:** Edit sonrası proje bilgisi API tarafında doğrulanabilmeli
- **Type:** Data Integrity / Consistency
- **Priority:** P0
- **Preconditions:** UI ve API erişimi mevcut olmalı; düzenlenmiş proje mevcut olmalı
- **Test Description:** Kullanıcı UI üzerinden proje bilgisini günceller, ardından API ile proje bilgisi sorgulanır
- **Expected Result:** UI’da yapılan güncelleme API tarafında da aynı şekilde görünmelidir
- **Automation Decision:** API automation

---

### PM-EP-007
- **Scenario ID:** PM-EP-007
- **Scenario Name:** Yetkili kullanıcı proje açıklamasını güncelleyebilmeli
- **Type:** Functional
- **Priority:** P1
- **Preconditions:** Projede açıklama alanı mevcut olmalı; kullanıcı edit yetkisine sahip olmalı
- **Test Description:** Kullanıcı proje açıklamasını değiştirip kaydeder
- **Expected Result:** Açıklama başarıyla güncellenir ve yeni değer görüntülenir
- **Automation Decision:** UI automation

---

### PM-EP-008
- **Scenario ID:** PM-EP-008
- **Scenario Name:** Proje açıklaması boşaltılarak kaydedilebilmeli veya sistem kuralına göre engellenmeli
- **Type:** Functional / Validation
- **Priority:** P2
- **Preconditions:** Açıklama alanı dolu bir proje mevcut olmalı
- **Test Description:** Kullanıcı mevcut proje açıklamasını silerek kaydetmeyi dener
- **Expected Result:** Ürünün kuralına göre davranış doğrulanmalı; ya boş açıklama kabul edilmeli ya da validasyon verilmelidir
- **Automation Decision:** Manual only

---

### PM-EP-009
- **Scenario ID:** PM-EP-009
- **Scenario Name:** Kullanıcı kendi yetkisi dışında bir projeyi düzenleyememeli
- **Type:** Authorization / Negative
- **Priority:** P0
- **Preconditions:** Edit yetkisi olmayan kullanıcı ve düzenlenemez hedef proje mevcut olmalı
- **Test Description:** Yetkisiz kullanıcı proje edit ekranına erişmeye veya update işlemi yapmaya çalışır
- **Expected Result:** Erişim reddedilmeli veya update işlemi başarısız olmalıdır
- **Automation Decision:** UI automation / API automation

---

### PM-EP-010
- **Scenario ID:** PM-EP-010
- **Scenario Name:** Normal user yalnız izinli olduğu projelerde edit yapabilmeli
- **Type:** Authorization + Functional
- **Priority:** P1
- **Preconditions:** Normal user için düzenleme yetkisi tanımlı bir proje mevcut olmalı
- **Test Description:** Normal user izinli olduğu projede edit işlemi yapar
- **Expected Result:** Güncelleme başarıyla tamamlanmalıdır
- **Automation Decision:** UI automation / API automation

---

### PM-EP-011
- **Scenario ID:** PM-EP-011
- **Scenario Name:** Kaydetme sonrası başarı mesajı veya beklenen kullanıcı geri bildirimi gösterilmeli
- **Type:** UX / Functional
- **Priority:** P2
- **Preconditions:** Düzenlenebilir proje mevcut olmalı
- **Test Description:** Kullanıcı geçerli değişiklik yapıp kaydeder
- **Expected Result:** Kullanıcıya işlemin başarılı olduğunu gösteren uygun geri bildirim sunulmalıdır
- **Automation Decision:** Not worth automating

---

### PM-EP-012
- **Scenario ID:** PM-EP-012
- **Scenario Name:** Değişiklik yapılmadan kaydetme davranışı kontrollü olmalı
- **Type:** Functional / UX
- **Priority:** P2
- **Preconditions:** Düzenlenebilir proje mevcut olmalı
- **Test Description:** Kullanıcı hiçbir alanı değiştirmeden save işlemi yapar
- **Expected Result:** Sistem hata vermemeli; davranış ya no-op save ya da bilgi mesajı şeklinde tutarlı olmalıdır
- **Automation Decision:** Not worth automating

---

### PM-EP-013
- **Scenario ID:** PM-EP-013
- **Scenario Name:** Aynı isimde başka proje varsa rename davranışı doğrulanmalı
- **Type:** Validation / Functional
- **Priority:** P1
- **Preconditions:** Aynı isimde veya hedef isimde başka bir proje mevcut olmalı
- **Test Description:** Kullanıcı mevcut projeyi başka bir projenin adıyla yeniden adlandırmaya çalışır
- **Expected Result:** Ürün kuralına göre duplicate name davranışı doğrulanmalı; kabul ediliyorsa kontrollü kabul edilmeli, engelleniyorsa anlamlı hata verilmelidir
- **Automation Decision:** Manual only

---

### PM-EP-014
- **Scenario ID:** PM-EP-014
- **Scenario Name:** Edit edilen proje adı proje listesinde güncel haliyle görünmeli
- **Type:** Functional / Consistency
- **Priority:** P1
- **Preconditions:** Proje başarıyla rename edilmiş olmalı
- **Test Description:** Kullanıcı proje adını güncelledikten sonra proje listesine döner
- **Expected Result:** Proje listesinde yeni ad görünmelidir; eski ad görünmemelidir
- **Automation Decision:** UI automation

---

### PM-EP-015
- **Scenario ID:** PM-EP-015
- **Scenario Name:** Edit edilen proje detay ekranında güncel bilgiler görünmeli
- **Type:** Functional / Consistency
- **Priority:** P1
- **Preconditions:** Proje başarıyla edit edilmiş olmalı
- **Test Description:** Kullanıcı kaydetme sonrası proje detayına bakar veya yeniden açar
- **Expected Result:** Güncellenen proje bilgileri detay ekranında doğru görünmelidir
- **Automation Decision:** UI automation

---

### PM-EP-016
- **Scenario ID:** PM-EP-016
- **Scenario Name:** API üzerinden yetkisiz proje güncelleme engellenmeli
- **Type:** API / Authorization / Negative
- **Priority:** P0
- **Preconditions:** Yetkisiz kullanıcı API credentials hazır olmalı; hedef proje mevcut olmalı
- **Test Description:** Yetkisiz kullanıcı API üzerinden proje update isteği gönderir
- **Expected Result:** API isteği reddedilmeli ve proje bilgisi değişmemelidir
- **Automation Decision:** API automation

---

### PM-EP-017
- **Scenario ID:** PM-EP-017
- **Scenario Name:** API üzerinden yetkili proje güncelleme başarılı olmalı
- **Type:** API / Functional
- **Priority:** P1
- **Preconditions:** Yetkili kullanıcı API credentials hazır olmalı; hedef proje mevcut olmalı
- **Test Description:** Yetkili kullanıcı API üzerinden proje adı veya açıklamasını günceller
- **Expected Result:** Güncelleme başarılı olmalı ve yeni değer sistemde görünmelidir
- **Automation Decision:** API automation

---

### PM-EP-018
- **Scenario ID:** PM-EP-018
- **Scenario Name:** Edit ekranında mevcut proje bilgileri ön yüklü gelmeli
- **Type:** Functional / UX
- **Priority:** P1
- **Preconditions:** Düzenlenebilir proje mevcut olmalı
- **Test Description:** Kullanıcı edit ekranını açar
- **Expected Result:** Mevcut proje adı ve varsa diğer alanlar mevcut değerleriyle formda görünmelidir
- **Automation Decision:** UI automation

---

## 4. Coverage Classification

### 4.1 Core Regression
Aşağıdaki senaryolar edit project fonksiyonu için çekirdek regresyon seti olarak değerlendirilir:

- PM-EP-001
- PM-EP-002
- PM-EP-005
- PM-EP-006
- PM-EP-009
- PM-EP-016

### 4.2 Extended Coverage
Aşağıdaki senaryolar ikinci aşama kapsam genişletme seti olarak ele alınır:

- PM-EP-003
- PM-EP-007
- PM-EP-010
- PM-EP-014
- PM-EP-015
- PM-EP-017
- PM-EP-018

### 4.3 Exploratory / Manual Discovery
Aşağıdaki senaryolar ürün davranışını keşfetmek veya business rule netleştirmek için öncelikle manuel değerlendirilecektir:

- PM-EP-004
- PM-EP-008
- PM-EP-013

### 4.4 Low Value / Optional
Aşağıdaki senaryolar düşük risk ve düşük tekrar değeri nedeniyle opsiyonel tutulur:

- PM-EP-011
- PM-EP-012

---

## 5. Automation Strategy Summary

Bu alt başlık için önerilen otomasyon yaklaşımı:

### UI Automation için uygun senaryolar
- PM-EP-001
- PM-EP-002
- PM-EP-003
- PM-EP-005
- PM-EP-007
- PM-EP-010
- PM-EP-014
- PM-EP-015
- PM-EP-018

### API Automation için uygun senaryolar
- PM-EP-006
- PM-EP-009
- PM-EP-010
- PM-EP-016
- PM-EP-017

### Manual Only
- PM-EP-004
- PM-EP-008
- PM-EP-013

### Not Worth Automating
- PM-EP-011
- PM-EP-012
