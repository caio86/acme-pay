package br.com.acmepay;

import br.com.acmepay.application.domain.models.CustomerDomain;
import br.com.acmepay.application.domain.exception.BalanceToWithdrawException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws BalanceToWithdrawException, Exception
    {
        CustomerDomain c1 = new CustomerDomain();
        CustomerDomain c2 = new CustomerDomain();
        CustomerDomain c3 = new CustomerDomain();

        c1.setId(1L);
        c2.setId(2L);
        c3.setId(3L);

        c1.setName("Carlos");
        c2.setName("Lucas");
        c3.setName("Gustavo");

        c1.setDocument("1234");
        c2.setDocument("1010");
        c3.setDocument("1020");

        c1.setEmail("carlos@google.com");
        c2.setEmail("lucas@email.com");
        c3.setEmail("gustavo@gustavo.com");

        c1.setPhone("83912341234");
        c2.setPhone("83910101010");
        c3.setPhone("83910201020");

        c1.create(c1);
        c2.create(c2);
        c3.create(c3);

        System.out.println(CustomerDomain.list());
        for (CustomerDomain customerDomain : CustomerDomain.list()) {
            System.out.println(customerDomain.getEmail());
        }

        CustomerDomain.delete(3L);

        System.out.println(CustomerDomain.list());
        for (CustomerDomain customerDomain : CustomerDomain.list()) {
            System.out.println(customerDomain.getEmail());
        }
    }
}
