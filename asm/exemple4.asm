.include beta.uasm
.include intio.uasm
.options tty

	CMOVE(pile,SP)
	BR(debut)
i:	 LONG(0)
j:	 LONG(20)
main:
	PUSH(LP)
	PUSH(BP)
	MOVE(SP,BP)
	ALLOCATE(0)
	RDINT()
	PUSH(R0)
	POP(R0)
	ST(R0,i)
	LD(i,R0)
	PUSH(R0)
	LD(j,R0)
	PUSH(R0)
	POP(R2)
	POP(R1)
	ADD(R1,R2,R0)
	PUSH(R0)
	POP(R0)
	WRINT()
return_main:
	DEALLOCATE(0)
	POP(BP)
	POP(LP)
	RTN()
debut:
	CALL(main)
	HALT()
pile:

