package com.abc3355.abc_wms_system.salesManager.View;

import com.abc3355.abc_wms_system.salesManager.controller.SalesManagerController;
import com.abc3355.abc_wms_system.salesManager.model.dto.BranchDTO;
import com.abc3355.abc_wms_system.salesManager.model.service.SalesManagerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SalesManagerView {

    public static void SalesManagerMain() {

        Scanner sc = new Scanner(System.in);
        SalesManagerController smController = new SalesManagerController();

        do {
            System.out.println("=========== 매출관리 메뉴 ============");
            System.out.println("1. 가맹점별 주문량 조회");
            System.out.println("2. 전체 주문 내역 조회");
            System.out.println("3. 전체 상품별 출고량 조회");
            System.out.println("9. 이전 메뉴로");
            System.out.print("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch(no) {
                case 1 : smController.selectByBranchAndDate(inputCode());break;
                case 2 : break;
                case 3 : break;
                case 9 : return;
                default:
                    System.out.println("잘못된 값입니다. 다시 입력해주세요.");
            }
        } while(true);
    }

    /**
     * 가맹점 정보와 조회 기간을 입력 받음
     */
    public static Map<String, String> inputCode() {

        Scanner sc = new Scanner(System.in);
        SalesManagerService smService = new SalesManagerService();
        PrintResult printResult = new PrintResult();

        Map<String, String> parameter = new HashMap<>();

        do {
            List<BranchDTO> branch = smService.getAllBranch();


            System.out.println("=========== 조회할 가맹점 선택 ============");
            printResult.printBranchList(branch);         // 가맹점 리스트 노출
            System.out.print("가맹점 번호를 입력하세요 : ");
            int no = sc.nextInt();
            sc.nextLine();

            if (no <= 0 && no > branch.size() ) {
                System.out.println("잘못된 값입니다. 다시 입력해주세요.");
            } else {
                parameter.put("no", String.valueOf(no));
                break;
            }
        } while (true);

        System.out.println("=========== 조회할 기간 선택 ============");
        System.out.print("기간을 선택하시려면 예를 입력하세요. (그 외 전체 검색) : ");

        if(sc.nextLine().equals("예")) {

            String start;
            String end;

            do {
                System.out.println("=========== 조회할 기간 선택 ============");
                System.out.print("조회 시작할 기간을 선택하세요. (ex : 20240517) : ");
                start = sc.nextLine();
                System.out.print("조회 종료할 기간을 선택하세요. (ex : 20240517) : ");
                end = sc.nextLine();

                if (checkDateFormat(start, end) == 0) { // 날짜 정상 입력 조건 확인
                    System.out.println("잘못된 값입니다. 다시 입력해주세요.");
                } else { break; }
            } while(true);

            parameter.put("startDate", start);
            parameter.put("endDate", end);
        }
        return parameter;
    }


    /**
     * 입력된 날짜가 정상값인지 확인하는 메서드
     * @param start
     * @param end
     * @return
     */
    private static int checkDateFormat(String start, String end) {
        int result = 0;
        int startInt = Integer.parseInt(start);
        int endInt = Integer.parseInt(end);

        if (start.length() != 8 && end.length() != 8) { return result; }    // 8자리수
        if (start == "" && end == "") { return result; }            // null 확인
        if (startInt < 20000000 && endInt < 20000000 ) { return result; }   // 숫자 최소치 확인
        if (startInt > endInt ) { return result; }                          // 시작 < 종료
        result = 1;
        return result;
    }

}
