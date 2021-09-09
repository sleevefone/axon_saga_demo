//package com.saga.example.axon.config;
//
//import org.axonframework.config.ProcessingGroup;
//import org.axonframework.eventhandling.*;
//import org.axonframework.eventhandling.replay.ResetContext;
//
//@AllowReplay // 1.
//@ProcessingGroup("card-summary")
//public class CardSummaryProjection {
//    //...
//    @EventHandler
//    @DisallowReplay // 2. - It is possible to prevent some handlers from being replayed
//    public void on(CardIssuedEvent event) {
//        // This event handler performs a "side effect",
//        //  like sending an e-mail or a sms.
//        // Neither, is something we want to reoccur when a
//        //  replay happens, hence we disallow this method
//        //  to be replayed
//    }
//
//    @EventHandler
//    public void on(CardRedeemedEvent event, ReplayStatus replayStatus) {
//        replayStatus.isReplay();
//        // We can wire a ReplayStatus here so we can see whether this
//        // event is delivered to our handler as a 'REGULAR' event or
//        // a 'REPLAY' event
//        // Perform event handling
//    }
//
//    @ResetHandler // 4. - This method will be called before replay starts
//    public void onReset(ResetC  ontext resetContext) {
//
//        //omitted state, command and event sourcing handlers
//        // Do pre-reset logic, like clearing out the projection table for a
//        // clean slate. The given resetContext is [optional], allowing the
//        // user to specify in what context a reset was executed.
//    }
//    //...
//}