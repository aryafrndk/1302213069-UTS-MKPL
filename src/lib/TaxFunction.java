package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	private static final double TAX_RATE = 0.05;
    private static final int BASE_TAX_FREE_INCOME = 54000000;
    private static final int MARRIED_TAX_FREE_INCOME_ADDITION = 4500000;
    private static final int CHILD_TAX_FREE_INCOME_ADDITION = 1500000;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

        int totalMonthlyIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int taxFreeIncome = calculateTaxFreeIncome(isMarried, numberOfChildren);
        int taxableIncome = totalMonthlyIncome - deductible - taxFreeIncome;
        int tax = (int) Math.round(TAX_RATE * taxableIncome);

        return Math.max(tax, 0);
    }
	
	private static int calculateTaxFreeIncome(boolean isMarried, int numberOfChildren) {
        int taxFreeIncome = BASE_TAX_FREE_INCOME;
        if (isMarried) {
            taxFreeIncome += MARRIED_TAX_FREE_INCOME_ADDITION;
        }
        taxFreeIncome += Math.min(numberOfChildren, 3) * CHILD_TAX_FREE_INCOME_ADDITION;
        return taxFreeIncome;
    }
	
}
