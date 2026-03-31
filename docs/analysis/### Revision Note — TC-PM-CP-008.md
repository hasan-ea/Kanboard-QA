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
- **Test Case Title:** Whitespace içeren project name girdilerinde create sonrası display ve edit davranışı tutarlı olmalı
- **Purpose:** Whitespace-only ve trim-sensitive project name girdilerinde sistemin create, list display ve edit ekranı davranışını tutarlılık açısından doğrulamak.
- **Priority:** High
- **Coverage Type:** Extended Coverage
- **Layer:** UI
- **Preconditions:**
    - Team project create yetkisine sahip bir kullanıcı hazır olmalı.
    - Create ekranı erişilebilir olmalı.
    - Proje listesi ve proje ayarları ekranı erişilebilir olmalı.
- **Test Data:**
    - `" "`
    - `"     "`
    - `" Project Alpha"`
    - `"Project Alpha "`
    - `"Project   Alpha"`
    - `"  Project   Alpha  "`
- **Steps:**
    1. Create ekranını aç.
    2. Veri setindeki project name değerlerinden birini gir.
    3. Project create işlemini tamamla.
    4. Oluşturulan projenin listede görünen adını kaydet.
    5. Proje detay/header alanında görünen adı kaydet.
    6. Project settings > Edit project ekranını aç.
    7. Edit input içindeki mevcut project name değerini kaydet.
    8. Üç görünümü karşılaştır.
    9. Aynı akışı diğer veri setleri için tekrarla.
- **Expected Results:**
    - Sistem whitespace içeren girdileri kabul edebilir veya normalize edebilir.
    - Ancak proje adı farklı ekranlarda açıklanamayan çelişkili biçimde gösterilmemelidir.
    - Liste görünümü, proje başlığı/detay görünümü ve edit input değeri deterministik davranmalıdır.
    - Eğer normalization uygulanıyorsa bu davranış aynı veri seti için tutarlı olmalıdır.
    - Whitespace-only input kabul ediliyorsa sistem kullanıcı açısından boş veya anlamsız görünen bir display üretmemelidir.
    - Beklenmeyen server error, broken UI veya bozuk kayıt oluşmamalıdır.
- **Postconditions / Cleanup:**
    - Oluşturulan disposable projeler silinmelidir.
- **Dependency:**
    - Project create helper
    - Project listing helper
    - Project settings / edit helper
- **Automation Candidate Note:**
    - UI observation-driven coverage için uygundur.
    - Blocking core regression yerine extended suite altında tutulmalıdır.
    - Assertion’lar “kesin reject” yerine “cross-screen consistency” odaklı olmalıdır.