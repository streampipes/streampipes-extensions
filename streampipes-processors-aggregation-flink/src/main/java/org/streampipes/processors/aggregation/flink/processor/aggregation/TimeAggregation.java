/*
 * Copyright 2017 FZI Forschungszentrum Informatik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.streampipes.processors.aggregation.flink.processor.aggregation;

import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.streampipes.model.runtime.Event;

public class TimeAggregation extends Aggregation implements WindowFunction<Event, Event, String, TimeWindow>,
        AllWindowFunction<Event, Event, TimeWindow> {

  public TimeAggregation(AggregationType aggregationType, String fieldToAggregate, String keyIdentifier) {
    super(aggregationType, fieldToAggregate, keyIdentifier);
  }

  public TimeAggregation(AggregationType aggregationType, String fieldToAggregate) {
    super(aggregationType, fieldToAggregate);
  }

  @Override
  public void apply(String key, TimeWindow window, Iterable<Event> input, Collector<Event> out) {
    process(input, out, key);
  }

  @Override
  public void apply(TimeWindow window, Iterable<Event> input, Collector<Event> out) throws Exception {
    process(input, out, null);
  }
}
