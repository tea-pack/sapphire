package com.github.tea_pack.sapphire.statistics.filters;

import com.github.tea_pack.sapphire.entities.*;


public class FullViewFilter implements Filter<FullView>{
	public static class Builder {
		public Filter<ViewInfo> viewInfoFilter = null;
		public Filter<Client> clientFilter = null;
		public Filter<Broadcast> broadcastFilter = null;
		public Filter<Channel> channelFilter = null;

		private Builder() {

		}

		public FullViewFilter build() {
			return new FullViewFilter(viewInfoFilter, clientFilter, broadcastFilter, channelFilter);
		}
	}

	public final Filter<ViewInfo> viewInfoFilter;
	public final Filter<Client> clientFilter;
	public final Filter<Broadcast> broadcastFilter;
	public final Filter<Channel> channelFilter;

	public FullViewFilter(Filter<ViewInfo> viewInfoFilter, Filter<Client> clientFilter, Filter<Broadcast> broadcastFilter, Filter<Channel> channelFilter) {
		this.viewInfoFilter = viewInfoFilter != null ? viewInfoFilter: new MockFilter<>();
		this.clientFilter = clientFilter  != null ? clientFilter: new MockFilter<>();
		this.broadcastFilter = broadcastFilter != null ? broadcastFilter: new MockFilter<>();
		this.channelFilter = channelFilter != null ? channelFilter: new MockFilter<>();
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public boolean test(FullView fullView) {
		return viewInfoFilter.test(fullView.info) &&
				clientFilter.test(fullView.client) &&
				broadcastFilter.test(fullView.broadcast) &&
				channelFilter.test(fullView.channel);
	}
}
