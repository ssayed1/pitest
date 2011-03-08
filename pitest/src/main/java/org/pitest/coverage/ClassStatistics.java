/*
 * Copyright 2010 Henry Coles
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package org.pitest.coverage;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pitest.util.MemoryEfficientHashMap;

public class ClassStatistics {

  private final String                className;
  private final Map<Integer, Integer> lineVists = new MemoryEfficientHashMap<Integer, Integer>();

  public ClassStatistics(final String className) {
    this.className = className;
  }

  public String getClassName() {
    return this.className;
  }

  public boolean wasVisited() {
    return !this.lineVists.isEmpty();
  }

  public Set<Integer> getUniqueVisitedLines() {
    final Set<Integer> uniqueVisits = new HashSet<Integer>(
        this.lineVists.size());
    uniqueVisits.addAll(this.lineVists.keySet());
    return uniqueVisits;
  }

  // public synchronized void clearLineCoverageStats() {
  // this.lineVists.clear();

  // }

  public synchronized void registerLineVisit(final int lineId) {
    this.lineVists.put(lineId, getNumberOfHits(lineId) + 1);
  }

  public Integer getNumberOfHits(final int lineId) {
    if (this.lineVists.containsKey(lineId)) {
      return this.lineVists.get(lineId);
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return "ClassStatistics [className=" + this.className + ", lineVists="
        + this.lineVists + "]";
  }

}