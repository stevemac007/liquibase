package liquibase.precondition;

import liquibase.database.Database;
import liquibase.exception.PreconditionErrorException;
import liquibase.exception.PreconditionFailedException;
import liquibase.precondition.core.PreconditionContainer;

public class StateConditional {

    private PreconditionContainer preconditionContainer = new PreconditionContainer();
    
    private boolean checked = false;
    private boolean passed = false;

    public PreconditionContainer getPreconditions() {
        return preconditionContainer;
    }

    public void setPreconditions(PreconditionContainer precond) {
        preconditionContainer = precond;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isPassed() {
        return passed;
    }

    public void check(Database database) {
        try {
            preconditionContainer.check(database, null, null);
            checked = true;
            passed = true;
        } catch (PreconditionFailedException e) {
//            LogFactory.getLogger().info(e.getMessage(), e);
            checked = true;
            passed = false;
        } catch (PreconditionErrorException e) {
//            LogFactory.getLogger().info(e.getMessage(), e);
            checked = true;
            passed = false;
        }
    }
}
