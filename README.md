# spring-batch

스프링 배치 공부

스프링배치 완벽가이드 2/e

---

# 2장 스프링 배치

## 스텝 분리의 장점
- 유연성 - 개발자가 재사용이 가능하게 구성할 수 있도록 여러 빌더 클래스를 제공한다.
- 유지 보수성 - 스텝 간의 영향이 미치지 않으면서 쉽게 각 스텝의 단위 테스트, 디버그, 변경을 할 수 있다.
- 확장성 - 스텝을 병렬로 실행할 수 있다. 하나의 스텝 내에서 처리할 일을 여러 스레드에 나눔으로써 해당 스텝의 코드를 병렬로 실행할 수 있다. 이러한 기능을 사용해 코드의 변경을 최소화하면서도 업무의 확정성에 대한 요구 사항을 충족할 수 있다.
- 신뢰성 - 스텝의 여러 단계(ItemWriter, ItemProcessor) 에 적용할 수 있는 강력한 오류 처리 방법을 제공하데, 예외 발생 시 해당 아이템의 처리를 재시도하거나 건너뛰기하는 등의 동작을 수행할 수 있다.

## 병렬화 방법
1. 다중 스레드 스텝을 통한 작업 분할
2. 전체 스텝의 병렬 실행
3. 비동기 ItemProcessor/ItemWriter 구성
4. 원격 청킹
5. 파티셔닝

## @EnableBatchProcessing

- JobRepository - 실행중인 잡의 상태를 기록하는데 사용됨
- JobLauncher - 잡을 구동하는 데 사용됨
- JobExplorer - JobRepository를 사용해 읽기 전용 작업을 수행하는데 사용됨
- JobRegistry - 특정한 런처 구현체를 사용할 때 잡을 찾는 용도로 사용됨
- PlatformTransactionManager - 잡 진행 과정에서 트랜잭션을 다루는 데 사용됨
- JobBuilderFactory - 잡을 생성하는 빌더
- StepBuilderFactory - 스텝을 생성하는 빌더

## @SpringBootApplication

- @ComponentScan과 @EnableAutoConfiguration의 결합한 메타 에너테이션이다.
- 데이터 소스뿐만 아니라 스프링 부트 기반의 적절한 자동 구성을 만들어 준다.

## RepeatStatus

- Tasklet이 완료 했는지 판별하는 열거형이다.

|Value|Description|
|-----|-----------|
|CONTIUABLE|처리할 작업이 남아 있다.|
|FINISHED|작업이 완료됐다.|

---

# 3장 예제 잡 애플리케이션

## 주요 배치 개념
- 다양한 입력 및 출력 방식
- 오류 처리 - 로깅, 오류가 있는 레코드 건너뛰기, 로직 수행 중 오류 발생 시 해당 로직 재시도하기 등
- 확장성 - 배치 처리 튜닝 방법