//package com.saga.example.axon;
//
//import org.axonframework.test.saga.SagaTestFixture;
//import org.springframework.data.jpa.domain.Specification;
//
//class TasksForStateSagaTest extends Specification {
//
//SagaTestFixture sagaFixture
//
//def setup() {
//    sagaFixture = new SagaTestFixture<>(TasksForStateSaga)
//}
//
//def 'should schedule task for the application state'() {
//    given:
//    def applicationId = '1'
//    def taskService = Mock(TaskService)
//    def tasks = [
//            ApplicationStateAwareTaskDefinition.builder().taskName('task1').build(),
//            ApplicationStateAwareTaskDefinition.builder().taskName('task2').build(),
//    ]
//    sagaFixture.registerResource(taskService)
//    sagaFixture.givenAggregate(applicationId)
//
//    when:
//    sagaFixture.whenPublishingA(new ApplicationStateChangedEvent(id: applicationId, newState: ApplicationState.NEW))
//            .expectActiveSagas(1)
//            .expectDispatchedCommandsMatching(payloadsMatching(
//            exactSequenceOf(
//                    equalTo(new ScheduleTaskCommand(applicationId: applicationId, targetState: ApplicationState.NEW, taskName: 'task1'),
//                            new IgnoreField(ScheduleTaskCommand, 'taskId')),
//                    equalTo(new ScheduleTaskCommand(applicationId: applicationId, targetState: ApplicationState.NEW, taskName: 'task2'),
//                            new IgnoreField(ScheduleTaskCommand, 'taskId')),
//                    andNoMore()
//            )
//    ))
//
//    then:
//    1 * taskService.getTasksByState(ApplicationState.NEW) >> tasks
//
//}