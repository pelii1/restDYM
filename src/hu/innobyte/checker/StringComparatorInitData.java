package hu.innobyte.checker;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StringComparatorInitData extends CheckerInitData {

    private String filePath;

    StringComparatorInitData(float distance) {
	super(distance);
    }

    @Builder
    StringComparatorInitData(float distance, String filePath) {
	super(distance);
	this.filePath = filePath;
    }

}
