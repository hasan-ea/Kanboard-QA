# Project Management - Project Views - Extended Coverage Test Cases

## 1. Amaç

Bu doküman, **Kanboard > Project Management > Project Views** fonksiyonu için belirlenmiş **Extended Coverage** senaryolarının detaylı test case karşılıklarını içerir.

---

## 2. Kapsamdaki Extended Coverage Senaryoları

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

---

## 3. Extended Coverage Test Cases

## TC-PM-PV-006
- **Test Case ID:** TC-PM-PV-006
- **Related Scenario ID:** PM-PV-003
- **Test Case Name:** Calendar görünümü due date içeren task’ları gösterebilmeli
- **Priority:** P1
- **Type:** Functional
- **Preconditions:** Due date içeren task’lar mevcut olmalı
- **Test Steps:**
  1. Projeyi aç
  2. Calendar view’a geç
  3. İlgili tarihleri kontrol et
- **Expected Result:** Due date içeren task’lar uygun tarihlerde görünmelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-007
- **Test Case ID:** TC-PM-PV-007
- **Related Scenario ID:** PM-PV-004
- **Test Case Name:** Gantt görünümü timeline verisini gösterebilmeli
- **Priority:** P1
- **Type:** Functional
- **Preconditions:** Start/end date veya timeline verisi mevcut olmalı
- **Test Steps:**
  1. Projeyi aç
  2. Gantt view’a geç
  3. Timeline satırlarını kontrol et
- **Expected Result:** Gantt görünümü doğru yüklenmeli ve timeline verisi görünmelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-008
- **Test Case ID:** TC-PM-PV-008
- **Related Scenario ID:** PM-PV-007
- **Test Case Name:** Search/filter sonucu list görünümünde doğru yansımalı
- **Priority:** P1
- **Type:** Functional / Search
- **Preconditions:** Filtrelenebilir task verisi mevcut olmalı
- **Test Steps:**
  1. List view’a geç
  2. Search/filter uygula
  3. Sonuçları kontrol et
- **Expected Result:** Liste görünümünde yalnız beklenen task’lar görünmelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-009
- **Test Case ID:** TC-PM-PV-009
- **Related Scenario ID:** PM-PV-008
- **Test Case Name:** Search/filter sonucu calendar görünümünde doğru yansımalı
- **Priority:** P1
- **Type:** Functional / Search
- **Preconditions:** Takvimde gösterilebilecek filtrelenebilir task verisi mevcut olmalı
- **Test Steps:**
  1. Calendar view’a geç
  2. Search/filter uygula
  3. Takvim hücrelerini kontrol et
- **Expected Result:** Yalnız filtreye uyan task’lar görünmelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-010
- **Test Case ID:** TC-PM-PV-010
- **Related Scenario ID:** PM-PV-009
- **Test Case Name:** Search/filter sonucu gantt görünümünde doğru yansımalı
- **Priority:** P1
- **Type:** Functional / Search
- **Preconditions:** Gantt’a uygun filtrelenebilir task verisi mevcut olmalı
- **Test Steps:**
  1. Gantt view’a geç
  2. Search/filter uygula
  3. Timeline sonuçlarını kontrol et
- **Expected Result:** Yalnız filtreye uyan öğeler görünmelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-011
- **Test Case ID:** TC-PM-PV-011
- **Related Scenario ID:** PM-PV-011
- **Test Case Name:** Board’da taşınan task list görünümünde yeni column ile görünmeli
- **Priority:** P1
- **Type:** Consistency
- **Preconditions:** Task board’da taşınmış olmalı
- **Test Steps:**
  1. Board’da task taşı
  2. List view’a geç
  3. İlgili task’ın column bilgisini kontrol et
- **Expected Result:** List görünümünde yeni column bilgisi görünmelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-012
- **Test Case ID:** TC-PM-PV-012
- **Related Scenario ID:** PM-PV-012
- **Test Case Name:** Viewer board ve task’ları read-only görüntüleyebilmeli
- **Priority:** P1
- **Type:** Authorization
- **Preconditions:** Viewer atanmış kullanıcı mevcut olmalı
- **Test Steps:**
  1. Viewer ile giriş yap
  2. Projeyi aç
  3. Board ve task detaylarını görüntüle
- **Expected Result:** Viewer görüntüleme yapabilmelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-013
- **Test Case ID:** TC-PM-PV-013
- **Related Scenario ID:** PM-PV-013
- **Test Case Name:** Viewer task drag & drop yapamamalı
- **Priority:** P1
- **Type:** Authorization / Negative
- **Preconditions:** Viewer ve board görünümü mevcut olmalı
- **Test Steps:**
  1. Viewer ile giriş yap
  2. Board’da task taşımayı dene
- **Expected Result:** Task hareket ettirilememelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-014
- **Test Case ID:** TC-PM-PV-014
- **Related Scenario ID:** PM-PV-014
- **Test Case Name:** Member board kullanımına erişebilmeli
- **Priority:** P1
- **Type:** Authorization / Functional
- **Preconditions:** Member atanmış kullanıcı mevcut olmalı
- **Test Steps:**
  1. Member ile giriş yap
  2. Board view’ı aç
  3. Temel board etkileşimlerini gözlemle
- **Expected Result:** Member board’u kullanabilmelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-015
- **Test Case ID:** TC-PM-PV-015
- **Related Scenario ID:** PM-PV-016
- **Test Case Name:** Advanced search syntax aktif view sonucunu doğru filtrelemeli
- **Priority:** P1
- **Type:** Search / Functional
- **Preconditions:** Advanced query ile eşleşen task verisi olmalı
- **Test Steps:**
  1. Uygun view’ı aç
  2. Advanced syntax ile sorgu gir
  3. Sonuçları kontrol et
- **Test Data:**
  - Query: `assignee:me status:open`
- **Expected Result:** Gelişmiş sorgu sonucu beklenen task’larla eşleşmelidir
- **Automation Candidate:** UI automation

## TC-PM-PV-016
- **Test Case ID:** TC-PM-PV-016
- **Related Scenario ID:** PM-PV-018
- **Test Case Name:** Board drag & drop sonucu API tarafında doğrulanabilmeli
- **Priority:** P1
- **Type:** Data Integrity / Consistency
- **Preconditions:** UI ve API erişimi hazır olmalı
- **Test Steps:**
  1. UI’da task’ı başka column’a taşı
  2. API ile ilgili task’ı sorgula
  3. Column bilgisini karşılaştır
- **Expected Result:** UI ve API yeni column bilgisinde tutarlı olmalıdır
- **Automation Candidate:** API automation