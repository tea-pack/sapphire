package com.github.tea_pack.sapphire.statistics;

import com.github.tea_pack.sapphire.entities.Broadcast;
import com.github.tea_pack.sapphire.entities.View;

import java.util.HashMap;
import java.util.List;

public class ChannelPopulartiy {
     public class Stats {
     	int totalTimeWatched;
     	int totalWatchers;
     }
     public HashMap<Broadcast, Stats> getBroadcastWatchTime(List<View> views) {
		 HashMap<Broadcast, Stats> map = new HashMap<>();
		 for(View view: views) {
			 if(map.containsKey(view.broadcast)){

			 }
		 }
     }
}
