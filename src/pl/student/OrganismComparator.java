package pl.student;

import java.util.Comparator;

public class OrganismComparator implements Comparator<Organism> {
    @Override
    public int compare(Organism o1, Organism o2) {
        int compareInitiative = o2.getInitiative()-o1.getInitiative();
        int compareAge = o2.getAge()-o1.getAge();
        if(compareInitiative != 0) {
            return compareInitiative;
        }
        else {
            return compareAge;
        }
    }
}
