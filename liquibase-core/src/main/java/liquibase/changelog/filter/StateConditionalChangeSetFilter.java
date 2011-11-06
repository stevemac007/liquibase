package liquibase.changelog.filter;

import liquibase.changelog.ChangeSet;
import liquibase.database.Database;

public class StateConditionalChangeSetFilter implements ChangeSetFilter {

    private final Database database;

    public StateConditionalChangeSetFilter(Database database) {
        this.database = database;
    }

    public boolean accepts(ChangeSet changeSet) {
        
        boolean passed = true;
        
        if (changeSet.getStateConditional() != null) {
            if (!changeSet.getStateConditional().isChecked()) {
                changeSet.getStateConditional().check(database);
            }
            
            passed = changeSet.getStateConditional().isPassed();
        }
        
        return passed;
    }
}
