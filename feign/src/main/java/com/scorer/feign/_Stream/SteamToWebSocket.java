package com.scorer.feign._Stream;

import com.scorer.feign._Stream.topic.TopicMatchTime_In;
import com.scorer.feign._Stream.topic.TopicSDQueueInfo_In;
import com.scorer.feign._Stream.topic.TopicSeasonDetails_In;
import com.scorer.feign._Stream.topic.TopicSeasonShowList_In;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.annotation.Resource;

//@EnableBinding({TopicMatchTime_In.class, TopicSDQueueInfo_In.class, TopicSeasonDetails_In.class, TopicSeasonShowList_In.class})
public class SteamToWebSocket {

//    @Resource
//    private SimpMessagingTemplate simpMessagingTemplate;

//    @StreamListener(TopicMatchTime_In.TOPIC)
//    public void receiveTopicMatchTimeMSG(Scorer_MatchTime scorer_matchTime) {
//        this.simpMessagingTemplate.convertAndSend("/topic/GetMatchTimeControl/season_id/" + scorer_matchTime.getSeason_id(), scorer_matchTime);
//    }
//
//    @StreamListener(TopicSeasonShowList_In.TOPIC)
//    public void receiveTopicSeasonShowListMSG(LiveShowList liveShowList) {
//        this.simpMessagingTemplate.convertAndSend("/topic/GetLiveShowList/season_id/" + liveShowList.getSeason_id(), liveShowList);
//    }
//
//    @StreamListener(TopicSeasonDetails_In.TOPIC)
//    public void receiveTopicSeasonDetailsMSG(Scorer_SeasonDetails scorer_seasonDetails) {
//        this.simpMessagingTemplate.convertAndSend("/topic/GetMatchShowList/season_id/" + scorer_seasonDetails.getSeason_id(), scorer_seasonDetails);
//    }
//
//    @StreamListener(TopicSDQueueInfo_In.TOPIC)
//    public void receiveTopicSDQueueInfoMSG(Scorer_SD_QueueInfo scorer_sd_queueInfo) {
//        this.simpMessagingTemplate.convertAndSend("/topic/SD/GetQueueInZoneGroup/zone_id/"+scorer_sd_queueInfo.getZone_id()+"/sd_group_id/"+scorer_sd_queueInfo.getSd_group_id(), scorer_sd_queueInfo);
//    }

}
