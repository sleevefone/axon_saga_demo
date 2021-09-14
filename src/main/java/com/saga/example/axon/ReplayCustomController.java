package com.saga.example.axon;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by mavlarn on 2018/5/28.
 */
@RestController
@RequestMapping("/replay")
@Api(tags = "6: ReplayCustomController")
public class ReplayCustomController {

    @Resource
    private TrackingEventProcessorService trackingEventProcessorService;

    @GetMapping("")
    @ApiOperation("createOrder")
    public void replayCustom() {
        trackingEventProcessorService.replay("customerProjector",1L);
    }


}
