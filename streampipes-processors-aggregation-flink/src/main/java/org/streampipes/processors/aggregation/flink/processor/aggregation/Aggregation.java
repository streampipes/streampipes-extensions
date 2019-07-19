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

import org.apache.flink.util.Collector;
import org.streampipes.model.runtime.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aggregation {

  private AggregationType aggregationType;
  private String fieldToAggregate;
  private String keyIdentifier;
  private Boolean keyedStream;

  // Used for keyed streams
  public Aggregation(AggregationType aggregationType, String fieldToAggregate, String keyIdentifier) {
    this.aggregationType = aggregationType;
    this.fieldToAggregate = fieldToAggregate;
    this.keyIdentifier = keyIdentifier;
    this.keyedStream = true;
  }

  // Used for not keyed streams
  public Aggregation(AggregationType aggregationType, String fieldToAggregate) {
    this.aggregationType = aggregationType;
    this.fieldToAggregate = fieldToAggregate;
    this.keyedStream = false;
  }


  protected Double getAggregate(List<Double> values) {
    if (aggregationType == AggregationType.AVG) {
      return values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    } else if (aggregationType == AggregationType.MAX) {
      return Collections.max(values);
    } else if (aggregationType == AggregationType.MIN) {
      return Collections.min(values);
    } else {
      return values.stream().mapToDouble(Double::doubleValue).sum();
    }
  }

  protected void process(Iterable<Event> input, Collector<Event> out, String key) {
    List<Double> values = new ArrayList<>();
    Event lastEvent = new Event();

    for (Event anInput : input) {
      lastEvent = anInput;
      if (!keyedStream || (lastEvent.getFieldBySelector(keyIdentifier).getAsPrimitive().getAsString()).equals(key)) {
        values.add(lastEvent.getFieldBySelector
                (fieldToAggregate).getAsPrimitive().getAsDouble());
      }
    }

    lastEvent.addField("aggregatedValue", getAggregate(values));
    out.collect(lastEvent);
  }
}
