package work.jyyh.common;

import cn.hutool.core.collection.CollUtil;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.junit.Test;

import java.util.List;

public class ApprovalProcessTest {
    // 审批流程整体
    @Data
    class ApprovalProcess {
        public Long id;
        public String name;
        public Boolean enable;
    }

    // 审批流程节点
    @Data
    class ApprovalProcessNode {
        public Long id;
        public String name;
        public Long processId;
        public Integer sort;
        public Boolean enable;
    }

    // 审批流程节点权限
    @Data
    class ApprovalProcessNodeAuthority {
        public Long id;
        public Long processNodeId;
        public String type;// 1-人 2-岗位 3-部门
        public String value;
    }

    // 审批流程整体运行
    @Data
    class ApprovalProcessRun {
        public Long id;
        public String name;
        public Long processId;
        public String useId;
    }

    // 审批流程节点运行
    @Data
    class ApprovalProcessNodeRun {
        public Long id;
        public String name;
        public Long processId;
        public Long processNodeId;
        public String status;// 0-初始化 1-正在进行 2-通过 3-不通过
        public String message;// 通过/不通过 说明
        public Long lastNodeId;
        public String paramJSON;
        public Integer sort;
    }

    // ApprovalProcess 知识审批 = ApprovalProcess.builder().id(1L).name("知识审批").enable(true).build();
    // ApprovalProcess 文档审批 = ApprovalProcess.builder().id(2L).name("文档审批").enable(true).build();
    // List<ApprovalProcess> approvalProcessList = CollUtil.newArrayList(知识审批, 文档审批);
    //
    // ApprovalProcessNode 知识审批_初审 = ApprovalProcessNode.builder().id(100L).name("初审").processId(知识审批.id).sort(10000).enable(true).build();
    // ApprovalProcessNode 知识审批_二审 = ApprovalProcessNode.builder().id(101L).name("二审").processId(知识审批.id).sort(10100).enable(true).build();
    // ApprovalProcessNode 知识审批_三审 = ApprovalProcessNode.builder().id(102L).name("三审").processId(知识审批.id).sort(10200).enable(true).build();
    // ApprovalProcessNode 知识审批_终审 = ApprovalProcessNode.builder().id(103L).name("终审").processId(知识审批.id).sort(10300).enable(true).build();
    // ApprovalProcessNode 文档审批_初审 = ApprovalProcessNode.builder().id(100L).name("初审").processId(文档审批.id).sort(10000).enable(true).build();
    // ApprovalProcessNode 文档审批_复审 = ApprovalProcessNode.builder().id(102L).name("复审").processId(文档审批.id).sort(10200).enable(true).build();
    // ApprovalProcessNode 文档审批_终审 = ApprovalProcessNode.builder().id(103L).name("终审").processId(文档审批.id).sort(10300).enable(true).build();
    // List<ApprovalProcessNode> approvalProcessNodeList = CollUtil.newArrayList(
    //         知识审批_初审, 知识审批_二审, 知识审批_三审, 知识审批_终审,
    //         文档审批_初审, 文档审批_复审, 文档审批_终审
    // );
    //
    // String 用户1 = "111111";
    // String 用户2 = "222222";
    // ApprovalProcessNodeAuthority 用户1权限_知识审批_初审 = ApprovalProcessNodeAuthority.builder().id(1L).processNodeId(知识审批_初审.id).type("1").value(用户1).build();
    // ApprovalProcessNodeAuthority 用户1权限_知识审批_二审 = ApprovalProcessNodeAuthority.builder().id(2L).processNodeId(知识审批_二审.id).type("1").value(用户2).build();
    //
    // @Test
    // public void run() {
    //     String 知识ID = "111111";
    //     String 用户1 = "111111";
    //     ApprovalProcessRun 知识审批_运行 = ApprovalProcessRun.builder().id(1L).name(知识审批.name).processId(知识审批.id).useId(知识ID).build();
    //     ApprovalProcessNodeRun 知识审批_初审_运行 = ApprovalProcessNodeRun.builder().id(100L).name("初审").processId(知识审批.id).processNodeId(知识审批_初审.id).status("0").sort(10000).build();
    //
    //     // 1.通过人ID查询流程
    //     // 2.通过流程ID查询当前节点
    //
    // }
}
