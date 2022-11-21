package org.example;

import java.time.Instant;
import java.util.Date;
import java.util.Vector;

public class Plan {
    private Vector<Instant> datesOfChanges;

    private Vector<String> planWithVersions;

    public Plan(){}

    public Vector<Instant> getDatesOfChanges() {
        return datesOfChanges;
    }
    public Vector<String> getPlanWithVersions() {
        return planWithVersions;
    }
    public String getLastVersion(){
        return planWithVersions.lastElement();
    }
    public void changePlan(String changed_plan){
        planWithVersions.add(changed_plan);
        datesOfChanges.add(Instant.now());
    }

}
