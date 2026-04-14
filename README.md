# Kanboard-QA

Kanboard üzerinde hazırlanmış, modül bazlı ilerleyen uçtan uca QA portföy projesidir.

Bu repo yalnızca UI test otomasyonu göstermek için değil;  
**iş analizi → test senaryosu → test case → UI/API otomasyon → CI düşüncesi** zincirini izlenebilir ve profesyonel şekilde sunmak için hazırlanmıştır.

Amaç, çok sayıda yüzeysel modül göstermek değil;  
**daha az modülle daha güçlü test tasarımı, daha temiz otomasyon yapısı ve daha iyi traceability** sunmaktır.

---

## Projenin Amacı

Bu proje ile şu alanlarda görünür ve sürdürülebilir bir QA yapısı kurmak hedeflenmiştir:

- business analysis (BA) çalışması
- modül bazlı test kapsamı oluşturma
- scenario ve test case dokümantasyonu
- UI otomasyon
- API doğrulama yaklaşımı
- deterministic test environment yönetimi
- traceability odaklı test tasarımı
- CI/CD’ye bağlanabilir framework temeli

Bu nedenle repo, tek seferlik script mantığıyla değil;  
**büyüyebilen ama kontrolsüz genişlemeyen** bir QA mühendisliği çalışması olarak kurgulanmıştır.

---

## Mevcut Durum

### Tamamlanan Modül

**Project Management > Create Project**

Bu modül için şu çalışmalar tamamlanmıştır:

- BA analizi
- test scenario dokümanı
- test case dokümanı
- UI ve API otomasyon kapsamı
- regression suite
- observation / known issue suite
- deterministic baseline restore ve verify yaklaşımı
- Jenkins pipeline temeli

### Mevcut Otomasyon Kapsamı

Create Project modülünde toplam **8 test case** otomasyona alınmıştır:

- **7 UI test**
- **1 API test**

Kapsam yalnızca happy path ile sınırlı değildir.  
Ağırlıklı olarak şu risk alanları hedeflenmiştir:

- authorized create behavior
- unauthorized create attempt
- validation behavior
- post-create visibility
- UI/API consistency
- observation-level known issue coverage

---

## Neden Scope Bilinçli Şekilde Dar Tutuldu?

Bu repo bilinçli olarak sınırlı scope ile ilerlemektedir.

Amaç:

- çok modül yazmış görünmek değil,
- seçilen modülleri daha sağlam dokümante etmek,
- otomasyonu daha sürdürülebilir kurmak,
- mülakatta “neden böyle tasarlandı?” sorusuna güçlü cevap verebilmektir.

Bu nedenle proje şu aşamada **az ama güçlü modül** yaklaşımını izlemektedir.

---

## Test Yaklaşımı

Projede aşağıdaki prensipler benimsenmiştir:

- modül bazlı ilerleme
- business rule / requirement odaklı düşünme
- scenario → test case → automation zinciri
- UI ve API katmanlarını mantıklı ayırma
- gerekli yerde hybrid doğrulama kullanma
- authorization, validation, visibility ve persistence risklerini öne alma
- core regression ile observation coverage ayrımını koruma
- mümkün olduğunca tekrar koşulabilir ve deterministic test yapısı kurma

---

## Traceability Yaklaşımı

Bu repo için traceability önemli bir tasarım hedefidir.

Örnek iz:

- **Business Rule / Analysis**
- **Scenario ID** → `PM-CP-###`
- **Test Case ID** → `TC-PM-CP-###`
- **Automation Class** → ilgili test case ile hizalı class adı
- **TestNG Description** → test case ve scenario referansı

Bu yapı sayesinde doküman ile otomasyon arasındaki bağ görünür tutulur.

---

## Teknik Stack

- **Java 17**
- **Selenium**
- **TestNG**
- **Maven**
- **Page Object Model (POM)**
- **Rest Assured**
- **JSON-RPC API yaklaşımı**
- **Docker / Docker Compose**
- **Jenkins**
- **SLF4J / Logback**

---

## Deterministic Environment Yaklaşımı

Projede test ortamı mümkün olduğunca tekrar üretilebilir olacak şekilde kurgulanmıştır.

Temel yaklaşım:

- Kanboard uygulaması Docker ile ayağa kaldırılır
- MariaDB container kullanılır
- baseline veri seti saklanır
- test öncesi baseline restore edilebilir
- restore sonrası environment verify edilir

Bu sayede test koşumları yalnızca “uygulama ayakta mı?” seviyesinde değil,  
**beklenen başlangıç verisi mevcut mu?** seviyesinde de kontrol edilir.

İlgili scriptler:

- `scripts/create-baseline-dump.sh`
- `scripts/restore-baseline.sh`
- `scripts/verify-baseline.sh`

---

## Test Data ve Konfigürasyon

Repo içinde örnek credential dosyası bulunur:

- `src/test/resources/config/credentials.example.properties`

Gerçek local credential dosyası repoya dahil edilmez:

- `src/test/resources/config/credentials.local.properties`

Bu yaklaşım sayesinde:

- hassas veriler repoya yazılmaz
- local ve CI ortamı ayrılabilir
- aynı framework farklı execution ortamlarında kullanılabilir

---

## Proje Yapısı

```text
Kanboard-QA/
├─ docs/
│  ├─ analysis/
│  ├─ bug-reports/
│  ├─ project-management/
│  └─ test-strategy/
├─ db-backup/
├─ scripts/
├─ src/
│  ├─ main/java/com/kanboard/
│  │  ├─ api/
│  │  ├─ base/
│  │  ├─ driver/
│  │  ├─ models/
│  │  ├─ pages/
│  │  └─ utils/
│  └─ test/
│     ├─ java/com/kanboard/tests/
│     │  ├─ base/
│     │  ├─ api/
│     │  └─ ui/
│     └─ resources/
│        ├─ config/
│        ├─ suites/
│        └─ testdata/
├─ Dockerfile.jenkins
├─ Jenkinsfile
├─ docker-compose.yml
├─ pom.xml
├─ mvnw
└─ README.md
```

---

## Nasıl Çalıştırılır?

> Aşağıdaki örnekler shell tabanlı komutlarla verilmiştir.

### 1. Ortamı başlat

```bash
docker compose up -d db
./scripts/restore-baseline.sh
docker compose up -d app
./scripts/verify-baseline.sh
```

### 2. Credential dosyasını hazırla

Örnek dosyayı baz alarak local credential dosyanı oluştur:

```bash
cp src/test/resources/config/credentials.example.properties src/test/resources/config/credentials.local.properties
```

### 3. Regression suite çalıştır

```bash
./mvnw test \
  -Dbrowser=chrome \
  -Dheadless=false \
  -Dbase.url=http://localhost:8080
```

### 4. Observation suite çalıştır

```bash
./mvnw test \
  -Dsurefire.suiteXmlFiles=src/test/resources/suites/modules/project-management/create-project/testng-create-project-observation.xml \
  -Dbrowser=chrome \
  -Dheadless=false \
  -Dbase.url=http://localhost:8080
```

---

## CI / Jenkins

Repo içinde Jenkins pipeline tanımı bulunmaktadır.

Pipeline akışında temel olarak şu adımlar yer alır:

- checkout
- tooling hazırlığı
- Docker environment başlatma
- DB readiness kontrolü
- baseline restore
- app readiness kontrolü
- environment verify
- regression suite çalıştırma
- isteğe bağlı observation suite çalıştırma
- surefire report ve docker log artifact arşivleme

Bu yapı henüz “kurumsal ölçekte tam CI platformu” iddiası taşımaz;  
ancak portföy seviyesi için **tekrar üretilebilir test koşumu ve pipeline düşüncesi** göstermeyi hedefler.

---

## Dokümantasyon Yapısı

Mevcut modül için dokümantasyon şu katmanlarda hazırlanmıştır:

1. **BA / Test Analysis**
2. **Test Scenarios**
3. **Test Cases**
4. **Bug Report / Observation Notes** *(uygunsa)*

Bu sayede repo yalnızca kod değil,  
**neden o testlerin yazıldığına dair karar sürecini de** gösterebilir.

---

## Known Issue / Observation Mantığı

Tüm testler ana regression suite içinde tutulmamaktadır.

Beklenen ürün davranışı net değilse veya mevcut davranış bug / observation niteliğindeyse,  
ilgili kapsam ayrı suite altında izole edilir.

Bu yaklaşımın amacı:

- ana regression güvenini korumak
- gözlemsel coverage’i kaybetmemek
- known issue ile gerçek regression kapsamını karıştırmamaktır

---

## Şu Anki Konumlandırma

Bu repo, “çok test yazılmış demo proje” olmaktan çok şu konuları göstermek için hazırlanmıştır:

- QA analitik düşünce yapısı
- kapsam yönetimi
- modül bazlı test tasarımı
- traceability
- sürdürülebilir automation framework yaklaşımı
- deterministic environment mantığı
- CI/CD’ye hazırlıklı test otomasyonu

---

## Sıradaki Adım

Planlanan ikinci modül:

**Project Type / Access Behavior**

Bu modülün seçilme nedeni:

- mevcut Create Project modülünü doğal olarak tamamlaması
- RBAC ve davranış farklarını görünür hale getirmesi
- mevcut seeded user / role yapısını yeniden kullanabilmesi
- portföy etkisinin yüksek olması

---

## Geliştirme Notu

Bu proje tamamen kör şekilde üretilmiş bir çalışma değildir.

Araştırma, alternatif çözüm denemeleri ve bazı teknik detaylarda AI destekli araçlardan yararlanılmıştır.  
Ancak kapsam, öncelik, test yaklaşımı ve yapı kararları bilinçli şekilde yönetilmiştir.

Odak, yalnızca kod üretmek değil;  
**QA mantığını kurmak, sınır çizmek ve izlenebilir bir yapı ortaya koymaktır.**
