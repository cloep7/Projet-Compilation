.include beta.uasm
.include intio.uasm
.options tty

	CMOVE(pile,SP)
	BR(debut)
i:	 LONG(10)
j:	 LONG(20)
k:	 LONG(0)
l:	 LONG(0)
main:
	PUSH(LP)
	PUSH(BP)
	MOVE(SP,BP)
	ALLOCATE(0)
	CMOVE(2,R0)
	PUSH(R0)
	POP(R0)
	ST(R0,k)
	LD(i,R0)
	PUSH(R0)
	CMOVE(3,R0)
	PUSH(R0)
	LD(j,R0)
	PUSH(R0)
	POP(R2)
	POP(R1)
	MUL(R1,R2,R0)
	PUSH(R0)
	POP(R2)
	POP(R1)
	ADD(R1,R2,R0)
	PUSH(R0)
	POP(R0)
	ST(R0,l)
return_main:
	DEALLOCATE(0)
	POP(BP)
	POP(LP)
	RTN()
debut:
	CALL(main)
	HALT()
pile:

