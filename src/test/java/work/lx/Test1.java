package work.lx;

import lombok.Data;

@Data
public class Test1 {
    private Test2 confirmForward;
}

@Data
class Test2 {
    private String acId;
}
