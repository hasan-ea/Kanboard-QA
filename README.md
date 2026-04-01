# Kanboard-QA

Kanboard uygulaması üzerinde modül bazlı ilerleyen, uçtan uca QA odaklı bir test mühendisliği projesidir.

Bu proje yalnızca test otomasyonu üretmek için değil; iş analizi, test kapsamı belirleme, senaryo tasarımı, test case yazımı, manuel test yaklaşımı, UI otomasyon ve API doğrulama mantığını birlikte göstermek için hazırlanmıştır.

---

## Projenin Amacı

Bu çalışmanın amacı, Kanboard üzerinde gerçek bir QA süreci kurgulayarak aşağıdaki alanlarda izlenebilir bir yapı oluşturmaktır:

- business analysis (BA) çalışması
- modül bazlı test kapsamı oluşturma
- test senaryoları ve test case dokümantasyonu
- UI test otomasyonu
- API doğrulama yaklaşımı
- traceability odaklı test tasarımı
- sürdürülebilir ve geliştirilebilir bir automation framework

Bu repo bir "tek seferlik script" çalışması değil; modül bazlı büyüyebilen, sade ama profesyonel bir QA portföy projesi olarak tasarlanmıştır.

---

## Mevcut Kapsam

### Tamamlanan Modül
**Project Management > Create Project**

Bu modül için şu çalışmalar tamamlanmıştır:

- BA analizi hazırlandı
- test senaryoları üretildi
- test case dokümanı oluşturuldu
- UI ve API açısından otomasyona uygun kapsam netleştirildi
- toplam **8 test case** otomasyona alındı
    - **7 UI test**
    - **1 API test**

### Seçili Scope Yaklaşımı
Başlangıçta proje daha geniş düşünülmüş olsa da, portföyde kaliteyi artırmak için kapsam kontrollü şekilde daraltılmıştır.

Bu repo finalde **2 modül** üzerinden ilerleyecektir:

1. **Create Project**
2. **Project Type / Access Behavior** *(planlanan ikinci modül)*

Bu tercih bilinçlidir. Amaç, çok fazla modülü yüzeysel göstermek yerine daha az modülü daha sağlam, izlenebilir ve profesyonel şekilde sunmaktır.

---

## Test Yaklaşımı

Projede aşağıdaki yaklaşım benimsenmiştir:

- modül bazlı ilerleme
- requirement / business rule odaklı düşünme
- scenario → test case → automation zinciri
- core regression ve extended coverage ayrımı
- UI ve API doğrulamalarını mantıklı şekilde ayırma
- gerektiğinde hybrid doğrulama kullanma
- authorization, validation, visibility ve data integrity risklerini dikkate alma

---

## Teknik Stack

- **Java**
- **Selenium**
- **TestNG**
- **Maven**
- **Page Object Model (POM)**
- **Rest Assured**
- **JSON-RPC API test yaklaşımı**
- **Docker / Docker Compose**
- **Logback / SLF4J**

---

## Proje Yapısı

```text
Kanboard-QA/
├─ docs/
├─ scripts/
├─ src/
│  ├─ main/
│  │  └─ java/com/kanboard/
│  │     ├─ api/
│  │     ├─ base/
│  │     ├─ driver/
│  │     ├─ models/
│  │     ├─ pages/
│  │     └─ utils/
│  └─ test/
│     ├─ java/com/kanboard/tests/
│     │  ├─ base/
│     │  ├─ ui/projectmanagement/createproject/
│     │  └─ api/projectmanagement/createproject/
│     └─ resources/
│        ├─ config/
│        ├─ suites/
│        └─ testdata/
├─ docker-compose.yml
├─ pom.xml
└─ README.md
```

---

## Dokümantasyon Yapısı

Create Project modülü için dokümantasyon 3 katmanda hazırlanmıştır:

1. **BA / Test Analizi**
2. **Test Scenarios**
3. **Test Cases**

Bu yapı sayesinde aşağıdaki izlenebilirlik hedeflenmiştir:

- iş kuralı → senaryo
- senaryo → test case
- test case → otomasyon

---

## Otomasyon Kapsamı

Şu an otomasyona alınan alanlar ağırlıklı olarak şu riskleri hedeflemektedir:

- authorized create behavior
- unauthorized create attempt
- validation davranışı
- create sonrası visibility
- UI ve API consistency kontrolü

Bu yaklaşım sayesinde otomasyon yalnızca happy path ile sınırlı kalmamıştır.

---

## Ortam Kurulumu

Projede test ortamı Docker Compose ile ayağa kaldırılmaktadır.

Genel akış:

1. Kanboard ve veritabanı container’larını başlat
2. test konfigürasyon dosyalarını hazırla
3. ilgili suite üzerinden testleri çalıştır

> Not: Bu repo portföy odaklı bir QA çalışmasıdır. Ortam ve veri yönetimi zaman içinde CI/CD uyumlu hale getirilecek şekilde geliştirilmektedir.

---

## Hedeflenen Sonraki Adımlar

- ikinci modülün BA, scenario ve case dokümanlarını hazırlamak
- ikinci modülün UI/API otomasyon kapsamını oluşturmak
- test suite yapısını modül bazlı netleştirmek
- Jenkins ile CI sürecine bağlamak
- raporlama ve execution görünürlüğünü iyileştirmek
- framework yapısını daha CI-friendly hale getirmek

---

## CI/CD Hedefi

Bu proje için hedeflenen CI yaklaşımı:

- Jenkins ile otomatik test tetikleme
- modül bazlı suite koşumu
- build + test sonucu görünürlüğü
- ileride smoke / regression ayrımı ile pipeline kurgusu

Şu an öncelik, modül bazlı test tasarımını ve framework temelini doğru oturtmaktır.

---

## Bu Projeyi Nasıl Konumluyorum?

Bu repo, çok sayıda test yazılmış bir demo proje olmaktan çok, şu konuları göstermek için hazırlanmıştır:

- kapsam yönetimi
- QA analitik düşünce yapısı
- test tasarım kalitesi
- otomasyona uygun dokümantasyon
- traceability
- sürdürülebilir framework yaklaşımı

Amaç, az modülle daha güçlü ve daha profesyonel bir kalite algısı oluşturmaktır.

---

## AI Kullanım Notu

Bu proje tamamen gözü kapalı şekilde üretilmiş bir çalışma değildir.

Analiz, kapsam yönetimi, modül seçimi, test yaklaşımı ve otomasyon yönü tarafımdan yönetilmiştir. Süreç içinde araştırma, alternatif çözüm üretimi, teknik doğrulama ve bazı geliştirme detaylarında AI destekli araçlardan yararlanılmıştır.

Bu nedenle proje, yalnızca kod üretimine değil; karar verme, yapı kurma ve QA mantığını yönetme becerisine odaklı olarak değerlendirilmelidir.

---

## İletişim

GitHub profilim üzerinden benimle iletişime geçebilirsiniz.
