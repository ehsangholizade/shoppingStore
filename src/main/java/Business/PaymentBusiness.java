package Business;

import Dao.PaymentDao;
import Model.Payment;
import java.util.List;
public class PaymentBusiness {
    private final PaymentDao paymentDao;
    public PaymentBusiness(){this.paymentDao = new PaymentDao();}
    public void creatPayment(Payment payment){ paymentDao.createPayment(payment);}
    public Payment getPaymentById(int paymentId){return paymentDao.getPaymentById(paymentId);}
    public void updatePayment(Payment payment){paymentDao.updatePayment(payment);}
    public void deletePayment(int paymentId){paymentDao.deletePayment(paymentId);}
    public List<Payment> getAllPayments(){return paymentDao.getAllPayments();}

    public double applyDiscount(Payment payment) {
        double paymentAmount = payment.getPaymentAmount();
        if (paymentAmount > 1000) {
            double discount = paymentAmount * 0.02;
            double discountedAmount = paymentAmount - discount;
            return discountedAmount;
        } else {
            if (paymentAmount < 1000){
                System.out.println("not include");
            }
        }
        return paymentAmount;
    }
}
