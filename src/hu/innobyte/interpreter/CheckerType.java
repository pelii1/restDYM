package hu.innobyte.interpreter;

import hu.innobyte.checker.Checker;
import hu.innobyte.checker.Repeater;
import hu.innobyte.checker.StringComparator;

public enum CheckerType {
    Repeater {
	public Checker getChecker() {
	    return new Repeater();
	}
    },
    StringComparator {
	public Checker getChecker() {
	    return new StringComparator();
	}
    };

    public Checker getChecker() {
	throw new MissingClassException();
    }
}
