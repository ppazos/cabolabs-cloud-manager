package com.cabolabs.cloud.plans

enum EBillingPeriod {

   WEEKLY(7 as short),
   BIWEEKLY(14 as short),
   FOUR_WEEKS(28 as short),
   MONTHLY(30 as short),
   QUARTERLY(90 as short),
   SEMESTERLY(180 as short),
   YEARLY(365 as short)

   private final short value

   short getValue()
   {
      return this.value
   }

   EBillingPeriod(short val)
   {
      this.value = val
   }

   static EBillingPeriod fromValue(short v)
   {
      for(EBillingPeriod e: EBillingPeriod.values())
      {
         if(e.value == v)
         {
           return e
         }
      }

      throw new IllegalArgumentException("Not valid EBillingPeriod value ${v}")
   }
}
