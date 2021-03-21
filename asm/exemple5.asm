.include beta.uasm
.include intio.uasm
.options tty

	CMOVE(pile,SP)
	BR(debut)
i:	 LONG(0)
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
	CMOVE(10,R0)
	PUSH(R0)
	POP(R2)
	POP(R1)
	CMPLT(R2,R1,R0)
	PUSH(R0)
	POP(R0)
	BF(R0,sinon1)
	CMOVE(1,R0)
	PUSH(R0)
	POP(R0)
	WRINT()
	BR(fsi1)
sinon1:
	CMOVE(2,R0)
	PUSH(R0)
	POP(R0)
	WRINT()
fsi1:
return_main:
	DEALLOCATE(0)
	POP(BP)
	POP(LP)
	RTN()
debut:
	CALL(main)
	HALT()
pile:

