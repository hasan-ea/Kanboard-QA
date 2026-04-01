### Revizyon Notu — TC-PM-CP-008
Test tasarımı gözden geçirilirken, whitespace-only proje adı beklentisi resmi Kanboard dokümantasyonu ve gerçek ürün davranışı ile yeniden değerlendirilmiştir.

Resmi doküman proje tiplerini ve oluşturma yetkilerini açıkça tanımlamaktadır; ancak whitespace-only veya trim-sensitive proje adları için açık bir validation kuralı belirtmemektedir. Bu nedenle TC-PM-CP-008, katı bir “reject” testi olmaktan çıkarılarak UI tutarlılığı ve normalization davranışını doğrulayan bir teste revize edilmiştir.

Revize edilen testin odak alanları:
- whitespace içeren inputların kabul davranışı
- listeleme ve detay ekranlarında görünen isim davranışı
- edit ekranındaki ham değer ile gösterilen değer arasındaki ilişki
- ekranlar arası proje adı tutarlılığı

Revize edilmiş Case
## TC-PM-CP-008
- **Test Case ID:** TC-PM-CP-008
- **Related Scenario ID:** PM-CP-008
- **Test Case Title:** Whitespace içeren project name girdilerinde create sonrası davranış kontrollü ve tutarlı olmalı
- **Purpose:** Whitespace-only ve trim-sensitive project name girdilerinde sistemin create sonrası gösterim ve edit davranışını doğrulamak; davranışın kontrollü, tutarlı ve kullanıcı açısından anlaşılabilir olup olmadığını değerlendirmek.
- **Priority:** High
- **Coverage Type:** Extended Coverage
- **Layer:** UI

### Preconditions
- Team project create yetkisine sahip bir kullanıcı hazır olmalı.
- Create ekranı erişilebilir olmalı.
- Proje listesi ve project edit ekranı erişilebilir olmalı.

### Test Data
- `" "`
- `"     "`
- `" Project Alpha"`
- `"Project Alpha "`
- `"Project   Alpha"`
- `"  Project   Alpha  "`

### Steps
1. Create ekranını aç.
2. Veri setindeki project name değerlerinden birini gir.
3. Project create işlemini submit et.
4. Submit sonrası sistem davranışını gözlemle.
5. Eğer create işlemi engellenirse:
  - validation veya eşdeğer kullanıcı geri bildirimini kontrol et.
  - başarılı create sonucuna ait ekran geçişi oluşmadığını doğrula.
6. Eğer create işlemi kabul edilirse:
  - Summary / detail görünümündeki proje adını kaydet.
  - Proje listesinde görünen adı kaydet.
  - Edit ekranındaki input değerini kaydet.
7. Aynı veri seti için ekranlar arası davranışı karşılaştır.
8. Akışı diğer veri setleri için tekrarla.

### Expected Results
- Sistem whitespace içeren girdileri tanımlı bir kurala göre ele almalıdır.
- Sistem bu girdileri reddedebilir, kabul edebilir veya normalize edebilir.
- Ancak aynı tip girdi için oluşan davranış tutarlı olmalıdır.
- Create engelleniyorsa kullanıcı anlamlı bir geri bildirim almalı ve başarılı create akışı oluşmamalıdır.
- Create kabul ediliyorsa proje adı farklı ekranlarda kullanıcıyı yanıltacak, açıklanamayan veya çelişkili biçimde gösterilmemelidir.
- Proje adı kullanıcı açısından boş, anlamsız veya ayırt edilemez bir sonuca dönüşmemelidir.
- Beklenmeyen server error, broken UI veya bozuk kayıt oluşmamalıdır.

### Postconditions / Cleanup
- Test verileri mevcut environment reset stratejisine göre temizlenmelidir.

### Dependency
- Project create helper
- Project listing helper
- Project edit helper

### Automation Candidate Note
- Bu test, whitespace handling davranışını ve ekranlar arası tutarlılığı gözlemlemek için uygundur.
- Assertion’lar ürünün olası reject / accept / normalize davranışlarını peşinen varsaymamalı; yalnızca tutarsız, yanıltıcı veya anlamsız sonuçları fail etmelidir.