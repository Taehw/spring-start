package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component //스프링 빈으로 등록하기 위한 어노테이션
//다른방법으로는 -> SpringConfig에 설정하는게 선호되긴함 - 인식이 잘되기때문에(이런거 쓰는구나)
@Aspect
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //공통관심사로 지정 - 해당 패키지 하위에 다 적용되게함 일단
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toString()); //START와 END를 모두 찍어준다.

        try {
            return joinPoint.proceed(); //다음 메서드로 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}