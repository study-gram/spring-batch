# spring-batch

스프링 배치 공부

스프링배치 완벽가이드 2/e

---

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