.include beta.uasm
.include intio.uasm
.options tty

	CMOVE(pile,SP)
	BR(debut)
i:	 LONG(0)
n:	 LONG(5)
main:
	PUSH(LP)
	PUSH(BP)
	MOVE(SP,BP)
	ALLOCATE(0)
	CMOVE(0,R0)
	PUSH(R0)
	POP(R0)
	ST(R0,i)
tq1:
	LD(i,R0)
	PUSH(R0)
	LD(n,R0)
	PUSH(R0)
	POP(R2)
	POP(R1)
	CMPLT(R1,R2,R0)
	PUSH(R0)
	POP(R0)
	BF(R0,ftq1)
	LD(i,R0)
	PUSH(R0)
	POP(R0)
	WRINT()
	LD(i,R0)
	PUSH(R0)
	CMOVE(1,R0)
	PUSH(R0)
	POP(R2)
	POP(R1)
	ADD(R1,R2,R0)
	PUSH(R0)
	POP(R0)
	ST(R0,i)
	BR(tq1)
ftq1:
return_main:
	DEALLOCATE(0)
	POP(BP)
	POP(LP)
	RTN()
debut:
	CALL(main)
	HALT()
pile:

